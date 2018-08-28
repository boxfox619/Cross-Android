package com.linkbit.android.presentation.splash

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
import com.linkbit.android.helper.Helper
import com.linkbit.android.presentation.Presenter
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
            Single.merge(coinRepository.loadAllCoinList(), friendRepository.loadFriendList(), walletRepository.loadWalletList()).subscribe({
                view.hideProgress()
                view.finishSplash()
            }, {
                view.hideProgress()
                view.showErrorMessage(ctx.getString(R.string.err_fail_load_init_data))
            })
        }
    }

    fun init() {
        Realm.init(this.ctx)
        val firebaseAuth = FirebaseAuth.getInstance()
        val user = firebaseAuth.currentUser
        if (user != null) {
            this.loadInitializeData()
        }
        view.registFBLogin(this)
    }

    override fun onSuccess(loginResult: LoginResult) {
        view.showProgress()
        val firebaseAuth = FirebaseAuth.getInstance()
        val credential = FacebookAuthProvider.getCredential(loginResult.accessToken.token)
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener { res ->
                    if (res.isSuccessful()) {
                        res.result.user.getIdToken(true).addOnCompleteListener { res ->
                            run {
                                if (res.isSuccessful()) {
                                    authRepository.login(res.result.token!!).subscribe {
                                        view.hideProgress()
                                        if (it) {
                                            loadInitializeData()
                                        } else {
                                            FirebaseAuth.getInstance().signOut()
                                            Helper.showToast(ctx, ctx.getString(R.string.err_fail_login))
                                        }
                                    }
                                } else {
                                    view.hideProgress()
                                }
                            }
                        }
                    } else {
                        view.hideProgress()
                        Helper.showToast(ctx, ctx.getString(R.string.err_fail_login))
                    }
                }
    }

    override fun onCancel() {
    }

    override fun onError(error: FacebookException) {
    }
}