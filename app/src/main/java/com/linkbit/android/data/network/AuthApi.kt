package com.linkbit.android.data.network

import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Query


interface AuthApi{
    @GET("signin")
    fun signin(@Query("token") token: String): Call<Void>

    @GET("logout")
    fun logout(): Call<Void>
}