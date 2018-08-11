package com.linkbit.android.presentation

import android.content.Context
import io.reactivex.disposables.CompositeDisposable

abstract class Presenter<out T : View> (protected val view : T){
    protected val disposables by lazy {
        CompositeDisposable()
    }

    protected var ctx : Context = view.getContext()
    open fun destory(){
        disposables.clear()
    }
}