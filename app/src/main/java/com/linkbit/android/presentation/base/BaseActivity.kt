package com.linkbit.android.presentation.base

import android.content.Context
import android.support.v7.app.AppCompatActivity


abstract class BaseActivity<out T : Presenter<*>> : AppCompatActivity() , View {

    abstract val presenter : T

    override fun onDestroy() {
        super.onDestroy()
        presenter.destory()
    }

    override fun getContext(): Context {
        return this
    }
}