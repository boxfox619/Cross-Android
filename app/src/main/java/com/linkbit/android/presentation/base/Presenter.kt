package com.linkbit.android.presentation.base

import android.content.Context
import rx.Subscription

abstract class Presenter<out T : View>(protected val view: T) {
    protected val disposables = ArrayList<Subscription>()

    protected fun getContext(): Context{
        return view.getContext()
    }
    open fun destory() {
        disposables.forEach { it.unsubscribe() }
    }
}