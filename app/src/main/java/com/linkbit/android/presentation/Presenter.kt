package com.linkbit.android.presentation

import android.content.Context
import rx.Subscription

abstract class Presenter<out T : View>(protected val view: T) {
    protected val disposables = ArrayList<Subscription>()

    protected var ctx: Context = view.getContext()
    open fun destory() {
        disposables.forEach { it.unsubscribe() }
    }
}