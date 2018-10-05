package com.linkbit.android.data.network

import com.linkbit.android.data.model.user.UserNetworkObject
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface AuthApi{
    @GET("signin")
    fun signin(@Query("token") token: String): Completable

    @GET("logout")
    fun logout(): Completable

    @GET("auth/info")
    fun info(): Single<UserNetworkObject>
}