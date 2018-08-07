package com.linkbit.android.ui.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.widget.LoginButton
import com.linkbit.android.R
import com.linkbit.android.presenter.SplashPresenter
import com.linkbit.android.ui.view.SplashView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_splash.*

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
        Picasso.get()
                .load(R.drawable.ic_app_icon)
                .resize(1600,1600)
                .onlyScaleDown()
                .into(iv_splash_logo)
    }

    override fun showProgress() {
        tv_splash_error.visibility = INVISIBLE
        progress_splash.visibility = VISIBLE
    }

    override fun hideProgress() {
        progress_splash.visibility = INVISIBLE
    }

    override fun finishSplash() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun showErrorMessage(msg: String) {
        hideProgress()
        tv_splash_error.visibility = VISIBLE
        tv_splash_error.text = msg
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
