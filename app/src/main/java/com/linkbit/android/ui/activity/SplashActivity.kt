package com.linkbit.android.ui.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.widget.LoginButton
import com.google.firebase.auth.FirebaseAuth
import com.linkbit.android.R
import com.linkbit.android.presenter.LoginPresenter
import com.linkbit.android.ui.view.LoginView

class SplashActivity : AppCompatActivity(), LoginView {

    lateinit var presenter: LoginPresenter
    lateinit var fbLoginButton: LoginButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        FacebookSdk.sdkInitialize(this)
        AppEventsLogger.activateApp(this)

        fbLoginButton = LoginButton(this)
        fbLoginButton.setReadPermissions("email", "public_profile")

        presenter= LoginPresenter()
        presenter.addView(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        presenter.fbCallbackManager.onActivityResult(requestCode, resultCode, data)
    }

    override fun getFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }
    override fun getContext(): Context {
        return this
    }
    override fun getFacebookLoginButton(): LoginButton {
        return fbLoginButton
    }
}
