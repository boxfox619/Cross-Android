package com.linkbit.android.ui.view

import com.linkbit.android.ui.base.BaseView
import com.google.firebase.auth.FirebaseAuth


interface LoginView : BaseView{
    fun getFirebaseAuth() : FirebaseAuth
}