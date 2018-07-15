package com.linkbit.android.presenter

import com.facebook.CallbackManager
import com.google.firebase.auth.FirebaseAuth
import com.linkbit.android.ui.view.SplashView
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.FacebookCallback
import com.google.firebase.auth.FacebookAuthProvider
import com.linkbit.android.R
import com.linkbit.android.helper.Helper
import com.linkbit.android.service.FriendService
import com.linkbit.android.service.WalletService
import io.realm.Realm
import rx.Observable


class SplashPresenter : BasePresenter<SplashView> {

    lateinit var splashView: SplashView
    lateinit var fbCallbackManager: CallbackManager

    fun loginWithFacebook() {
        splashView.let {
            it.getFacebookLoginButton()
            it.getFacebookLoginButton().registerCallback(fbCallbackManager, object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    val firebaseAuth = FirebaseAuth.getInstance()
                    val credential = FacebookAuthProvider.getCredential(loginResult.accessToken.token)
                    firebaseAuth.signInWithCredential(credential)
                            .addOnCompleteListener({
                                if (it.isSuccessful()) {
                                    loadInitializeData()
                                } else {
                                    Helper.showToast(splashView.getContext(), splashView.getContext().getString(R.string.err_fail_login))
                                }
                            })
                }

                override fun onCancel() {
                }

                override fun onError(error: FacebookException) {
                }
            })
        }
    }

    fun loadInitializeData() {
        splashView.let {
            splashView.showProgress()
            Observable.just(WalletService.instance.loadTotalTransactionList(it.getContext()),
                    WalletService.instance.loadWalletList(it.getContext()),
                    FriendService.instance.loadFriendList(it.getContext()))
                    .subscribe({ splashView.finishSplash() },
                            {splashView.hideProgress()
                            splashView.showErrorMessage(splashView.getContext().getString(R.string.err_fail_load_init_data))})
        }
    }

    fun init() {
        val firebaseAuth = FirebaseAuth.getInstance()
        val user = firebaseAuth.currentUser
        if (user != null) {
            this.loadInitializeData()
        }
    }

    override fun addView(view: SplashView) {
        this.splashView = view
        fbCallbackManager = CallbackManager.Factory.create()
        Realm.init(this.splashView.getContext())
    }

    override fun removeView() {
        this.splashView == null
    }
}

