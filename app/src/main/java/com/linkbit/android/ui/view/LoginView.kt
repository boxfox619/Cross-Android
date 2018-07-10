package com.linkbit.android.ui.view

import com.facebook.login.widget.LoginButton
import com.linkbit.android.ui.base.BaseView
import com.google.firebase.auth.FirebaseAuth


interface LoginView : BaseView{
    fun getFirebaseAuth() : FirebaseAuth
    fun getFacebookLoginButton() : LoginButton
}