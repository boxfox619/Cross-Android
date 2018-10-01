package com.linkbit.android.presentation.view.splash

import com.facebook.FacebookCallback
import com.facebook.login.LoginResult
import com.linkbit.android.presentation.base.View


interface SplashView : View {
    fun registFBLogin(fbCallback : FacebookCallback<LoginResult>) : Unit
    fun showProgress(): Unit
    fun hideProgress(): Unit
    fun showErrorMessage(msg: String): Unit
    fun finishSplash(): Unit
    fun setVisibleLoginButtons(visible: Boolean): Unit
}