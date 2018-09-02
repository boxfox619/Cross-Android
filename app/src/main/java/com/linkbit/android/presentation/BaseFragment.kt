package com.linkbit.android.presentation

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup


abstract class BaseFragment <T : Presenter<*>> :  Fragment(), View{
    lateinit var presenter : T

    override fun onDestroy() {
        super.onDestroy()
        presenter.destory()
    }
}