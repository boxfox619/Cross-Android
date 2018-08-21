package com.linkbit.android.presentation.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.linkbit.android.R
import com.linkbit.android.presentation.BaseActivity
import com.linkbit.android.presentation.main.MainActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity<SplashPresenter>(), SplashView {
    override var presenter = SplashPresenter(this)
    lateinit var fbLoginButton: LoginButton
    lateinit var fbCallbackManager: CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        FacebookSdk.sdkInitialize(this)
        AppEventsLogger.activateApp(this)
        fbCallbackManager = CallbackManager.Factory.create()
        fbLoginButton = LoginButton(this)
        fbLoginButton.setReadPermissions("email", "public_profile")
        fbLoginButton.setOnClickListener({fbLoginButton.performClick()})
        Picasso.get()
                .load(R.drawable.ic_app_icon)
                .resize(1000,1000)
                .onlyScaleDown()
                .into(iv_splash_logo)
        presenter.init()
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

    override fun registFBLogin(fbCallback: FacebookCallback<LoginResult>) {
        fbLoginButton.registerCallback(this.fbCallbackManager, fbCallback)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        fbCallbackManager.onActivityResult(requestCode, resultCode, data)
    }
    override fun getContext(): Context {
        return this
    }
}
