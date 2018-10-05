package com.linkbit.android.presentation.base

import android.content.Context
import io.reactivex.disposables.Disposable
import rx.Subscription

abstract class Presenter<out T : View>(protected val view: T) {
    protected val disposables = ArrayList<Disposable>()

    protected fun getContext(): Context{
        return view.getContext()
    }
    open fun destory() {
        disposables.forEach { it.dispose() }
    }
}