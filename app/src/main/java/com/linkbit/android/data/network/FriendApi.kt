package com.linkbit.android.data.network

import com.linkbit.android.data.model.user.UserNetworkObject
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.DELETE
import retrofit2.http.PUT
import retrofit2.http.GET
import retrofit2.http.*


interface FriendApi{

    @GET("search/account/list")
    fun searchUsers(@Query("text") text: String): Single<List<UserNetworkObject>>

    @GET("search/account")
    fun searchUser(@Query("uid") uid: String): Single<UserNetworkObject>

    @GET("friend")
    fun friendList(): Single<List<UserNetworkObject>>

    @PUT("friend")
    fun addFriend(@Body uid: String): Completable

    @DELETE("friend")
    fun deleteFriend(@Body uid: String): Completable
}