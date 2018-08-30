package com.linkbit.android.data.network

import com.linkbit.android.data.model.user.UserNetworkObject
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Query


interface AuthApi{
    @GET("signin")
    fun signin(@Query("token") token: String): Call<Void>

    @GET("logout")
    fun logout(): Call<Void>

    @GET("info")
    fun info(): Call<UserNetworkObject>
}