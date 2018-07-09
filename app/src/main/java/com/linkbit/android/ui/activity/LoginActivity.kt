package com.linkbit.android.ui.activity

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.linkbit.android.R
import com.linkbit.android.presenter.LoginPresenter
import com.linkbit.android.ui.view.LoginView
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity(), LoginView {

    lateinit var  mAuth : FirebaseAuth
    lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth= FirebaseAuth.getInstance()

        presenter= LoginPresenter()
        presenter.addView(this)
        presenter.login()
    }

    override fun onStart() {
        super.onStart()
        mAuth.addAuthStateListener(presenter.onAuthState())
    }

    override fun getFirebaseAuth(): FirebaseAuth {
        return mAuth
    }

    override fun getContext(): Context {
        return this
    }

}
