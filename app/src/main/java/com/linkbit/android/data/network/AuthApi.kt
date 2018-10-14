package com.linkbit.android.data.network

import com.linkbit.android.data.model.user.UserNetworkObject
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*


interface AuthApi{
    @POST("auth")
    fun signin(@Field("token") token: String): Completable

    @GET("auth/logout")
    fun logout(): Completable

    @GET("auth")
    fun info(): Single<UserNetworkObject>

    @DELETE("auth")
    fun unRegister(): Completable
}