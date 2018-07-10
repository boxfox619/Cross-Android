package com.linkbit.android.presenter

import com.facebook.CallbackManager
import com.google.firebase.auth.FirebaseAuth
import com.linkbit.android.ui.view.LoginView
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.FacebookCallback
import com.google.firebase.auth.FacebookAuthProvider


class LoginPresenter : BasePresenter<LoginView>{

    lateinit var loginView: LoginView
    lateinit var fbCallbackManager: CallbackManager


    fun loginWithFacebook(){
        loginView.let {
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

    fun onAuthState() : FirebaseAuth.AuthStateListener{
        return FirebaseAuth.AuthStateListener {
            firebaseAuth->firebaseAuth?.let {
                //login state check
            }
        }
    }

    override fun addView(view: LoginView) {
        fbCallbackManager = CallbackManager.Factory.create()
        this.loginView=view
    }

    override fun removeView() {
        this.loginView==null
    }
}

