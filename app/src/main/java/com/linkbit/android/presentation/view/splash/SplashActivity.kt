package com.linkbit.android.presentation.view.splash

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.linkbit.android.R
import com.linkbit.android.presentation.base.BaseActivity
import com.linkbit.android.presentation.view.main.MainActivity
import com.linkbit.android.presentation.view.pin.PinActivity
import com.linkbit.android.presentation.view.setting.SettingsActivity
import com.linkbit.android.presentation.view.signin.AnonymousLoginActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_splash.*

const val ANONYMOUS_LOGIN_RESULT = 100

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
        Picasso.get()
                .load(R.drawable.ic_app_icon)
                .resize(1000, 1000)
                .onlyScaleDown()
                .into(iv_splash_logo)
        btn_fb_login.setOnClickListener { fbLoginButton.performClick() }
        btn_anonymous_login.setOnClickListener { signinWithAnonymous()  }
        presenter.init()
    }

    private fun signinWithAnonymous(){
        //@TODO check signin or direct start (계정 없이 시작하기 / 백업등의 기능을 사용하기 위한 가입)
        startActivityForResult(Intent(this, AnonymousLoginActivity::class.java), ANONYMOUS_LOGIN_RESULT)
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

        if (FacebookSdk.isFacebookRequestCode(requestCode)) {
            fbCallbackManager.onActivityResult(requestCode, resultCode, data)
        }else if (requestCode == ANONYMOUS_LOGIN_RESULT) {
            if (resultCode == Activity.RESULT_OK) {
                this.presenter.init()
            }
        }
    }

    override fun getContext(): Context {
        return this
    }

    override fun setVisibleLoginButtons(visible: Boolean) {
        if (visible) {
            layout_splash_buttons.visibility = View.VISIBLE
        } else {
            layout_splash_buttons.visibility = View.INVISIBLE
        }
    }
}
