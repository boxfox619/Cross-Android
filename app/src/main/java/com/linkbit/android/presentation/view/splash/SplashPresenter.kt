package com.linkbit.android.presentation.view.splash

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.FacebookCallback
import com.google.firebase.auth.FacebookAuthProvider
import com.linkbit.android.R
import com.linkbit.android.data.repository.AuthRepository
import com.linkbit.android.data.repository.CoinRepository
import com.linkbit.android.data.repository.FriendRepository
import com.linkbit.android.data.repository.WalletRepository
import com.linkbit.android.helper.ToastHelper
import com.linkbit.android.presentation.base.Presenter
import io.realm.Realm
import rx.Single

class SplashPresenter(view: SplashView) : Presenter<SplashView>(view), FacebookCallback<LoginResult> {
    val authRepository = AuthRepository(view.getContext())
    val coinRepository = CoinRepository(view.getContext())
    val friendRepository = FriendRepository(view.getContext())
    val walletRepository = WalletRepository(view.getContext())

    override fun destory() {}

    fun loadInitializeData() {
        view.let {
            view.showProgress()
            Single.concat(authRepository.loadAuthData(), coinRepository.loadAllCoinList(), friendRepository.loadFriendList(), walletRepository.loadWalletList()).last().subscribe({
                view.hideProgress()
                view.finishSplash()
            }, {
                Log.d("Splash", "Fail to load initalize data")
                Log.d("Splash", it.message)
                view.hideProgress()
                view.showErrorMessage(getContext().getString(R.string.err_fail_load_init_data))
            })
        }
    }

    fun init() {
        Realm.init(this.getContext())
        val firebaseAuth = FirebaseAuth.getInstance()
        val user = firebaseAuth.currentUser
        if (user != null) {
            user.getIdToken(true).addOnCompleteListener { res ->
                if (res.isSuccessful) {
                    view.setVisibleLoginButtons(false)
                    this.loadInitializeData()
                } else {
                    view.setVisibleLoginButtons(true)
                }
            }
        } else {
            view.setVisibleLoginButtons(true)
            view.registFBLogin(this)
        }
    }

    override fun onSuccess(loginResult: LoginResult) {
        view.showProgress()
        view.setVisibleLoginButtons(false)
        val firebaseAuth = FirebaseAuth.getInstance()
        val credential = FacebookAuthProvider.getCredential(loginResult.accessToken.token)
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener { res ->
                    if (res.isSuccessful()) {
                        res.result.user.getIdToken(true).addOnCompleteListener { res ->
                            run {
                                if (res.isSuccessful()) {
                                    authRepository.login(res.result.token!!).subscribe {
                                        if (it) {
                                            loadInitializeData()
                                        } else {
                                            Log.d("Splash", "Fail to login")
                                            FirebaseAuth.getInstance().signOut()
                                            ToastHelper.showToast(getContext(), getContext().getString(R.string.err_fail_login))
                                            view.hideProgress()
                                            view.setVisibleLoginButtons(true)
                                        }
                                    }
                                } else {
                                    view.hideProgress()
                                    view.setVisibleLoginButtons(true)
                                }
                            }
                        }
                    } else {
                        view.hideProgress()
                        view.setVisibleLoginButtons(true)
                        ToastHelper.showToast(getContext(), getContext().getString(R.string.err_fail_login))
                    }
                }
    }

    override fun onCancel() {
    }

    override fun onError(error: FacebookException) {
    }
}