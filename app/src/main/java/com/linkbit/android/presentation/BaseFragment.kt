package com.linkbit.android.presentation

import android.app.Fragment
import android.content.Context


abstract class BaseFragment <out T : Presenter<*>> :  Fragment(), View{

    abstract val presenter : T

    override fun onDestroy() {
        super.onDestroy()
        presenter.destory()
    }

    override fun getContext(): Context {
        return this.getContext();
    }
}