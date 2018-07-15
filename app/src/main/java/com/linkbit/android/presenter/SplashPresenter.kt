package com.linkbit.android.presenter

import com.facebook.CallbackManager
import com.google.firebase.auth.FirebaseAuth
import com.linkbit.android.ui.view.SplashView
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.FacebookCallback
import com.google.firebase.auth.FacebookAuthProvider
import com.linkbit.android.service.FriendService
import com.linkbit.android.service.WalletService
import rx.Observable


class SplashPresenter : BasePresenter<SplashView>{

    lateinit var splashView: SplashView
    lateinit var fbCallbackManager: CallbackManager

    fun loginWithFacebook(){
        splashView.let {
            it.getFacebookLoginButton()
            it.getFacebookLoginButton().registerCallback(fbCallbackManager, object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    val firebaseAuth = it.getFirebaseAuth()
                    val credential = FacebookAuthProvider.getCredential(loginResult.accessToken.token)
                    firebaseAuth.signInWithCredential(credential)
                            .addOnCompleteListener({
                                if (it.isSuccessful()) {
                                    val user = firebaseAuth.getCurrentUser()
                                    // Sign in success
                                } else {
                                    // Sign in fail
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

    fun loadInitializeData(){
        splashView.let {
            splashView.showProgress()
            Observable.just(WalletService.instance.loadTotalTransactionList(it.getContext()),
                    WalletService.instance.loadWalletList(it.getContext()),
                    FriendService.instance.loadFriendList(it.getContext()))
                    .subscribe({
                        splashView.finishSplash()
                    })
        }
    }

    fun onAuthState() : FirebaseAuth.AuthStateListener{
        return FirebaseAuth.AuthStateListener {
            firebaseAuth->firebaseAuth?.let {
                //login state check
            }
        }
    }

    override fun addView(view: SplashView) {
        fbCallbackManager = CallbackManager.Factory.create()
        this.splashView=view
    }

    override fun removeView() {
        this.splashView==null
    }
}

