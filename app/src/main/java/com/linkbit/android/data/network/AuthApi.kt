package com.linkbit.android.data.network

import com.linkbit.android.data.model.auth.SigninNetworkObject
import retrofit2.http.GET
import retrofit2.Call


interface AuthApi{
    @GET("signin/")
    fun signin(): Call<Void>

    @GET("logout/")
    fun logout(): Call<Void>
}