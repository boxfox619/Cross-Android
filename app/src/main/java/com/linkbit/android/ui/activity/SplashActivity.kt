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
import com.linkbit.android.presenter.SplashPresenter
import com.linkbit.android.ui.view.SplashView

class SplashActivity : AppCompatActivity(), SplashView {

    lateinit var presenter: SplashPresenter
    lateinit var fbLoginButton: LoginButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        FacebookSdk.sdkInitialize(this)
        AppEventsLogger.activateApp(this)

        fbLoginButton = LoginButton(this)
        fbLoginButton.setReadPermissions("email", "public_profile")

        presenter= SplashPresenter()
        presenter.addView(this)
        fbLoginButton.setOnClickListener({presenter.loginWithFacebook()})
        presenter.init()
    }

    override fun showProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun finishSplash() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showErrorMessage(msg: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        presenter.fbCallbackManager.onActivityResult(requestCode, resultCode, data)
    }
    override fun getContext(): Context {
        return this
    }
    override fun getFacebookLoginButton(): LoginButton {
        return fbLoginButton
    }
}
