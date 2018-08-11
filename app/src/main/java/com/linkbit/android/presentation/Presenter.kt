package com.linkbit.android.presentation

import android.content.Context

abstract class Presenter<out T : View> (protected val view : T){
    protected var ctx : Context = view.getContext()
    abstract fun destory() //unsubscribe observers
}