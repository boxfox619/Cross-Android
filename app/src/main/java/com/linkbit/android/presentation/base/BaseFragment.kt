package com.linkbit.android.presentation.base

import android.app.Fragment


abstract class BaseFragment <T : Presenter<*>> :  Fragment(), View {
    lateinit var presenter : T

    override fun onDestroy() {
        super.onDestroy()
        presenter.destory()
    }
}