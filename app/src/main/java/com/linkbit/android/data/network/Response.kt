package com.linkbit.android.data.network

import android.content.*
import com.linkbit.android.helper.ToastHelper
import retrofit2.*
import retrofit2.Response

abstract class Response<T>(private val context: Context) : Callback<T> {

    override fun onFailure(call: Call<T>?, t: Throwable?) {
        ToastHelper.showToast(context, "네트워크 오류")
        setResponseData(400, null)
    }

    override fun onResponse(call: Call<T>?, response: Response<T>?) {
        response?.run { setResponseData(code(), body()) }
    }

    abstract fun setResponseData(code: Int, data: T?)

    fun isSuccess(code: Int): Boolean {
        return (code >= 200 && code < 300)
    }

}