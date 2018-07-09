package com.linkbit.android.presenter

interface BasePresenter<T> {

    fun addView(view : T)
    fun removeView()
}