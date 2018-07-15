package com.linkbit.android.ui.view

import com.facebook.login.widget.LoginButton
import com.linkbit.android.ui.base.BaseView
import com.google.firebase.auth.FirebaseAuth


interface SplashView : BaseView{
    fun getFacebookLoginButton() : LoginButton
    fun showProgress(): Unit
    fun hideProgress(): Unit
    fun showErrorMessage(msg: String): Unit
    fun finishSplash(): Unit
}