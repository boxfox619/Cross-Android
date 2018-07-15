package com.linkbit.android.ui.view

import com.facebook.login.widget.LoginButton
import com.linkbit.android.ui.base.BaseView
import com.google.firebase.auth.FirebaseAuth


interface SplashView : BaseView{
    fun getFirebaseAuth() : FirebaseAuth
    fun getFacebookLoginButton() : LoginButton
    fun showProgress(): Unit
    fun finishSplash(): Unit
}