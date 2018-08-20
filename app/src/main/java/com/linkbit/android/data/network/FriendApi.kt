package com.linkbit.android.data.network

import com.linkbit.android.data.model.user.UserNetworkObject
import retrofit2.http.DELETE
import retrofit2.http.PUT
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.*


interface FriendApi{

    @GET("search/account/")
    fun searchUsers(@Query("text") text: String): Call<List<UserNetworkObject>>

    @GET("friend")
    fun friendList(): Call<List<UserNetworkObject>>

    @PUT("friend")
    fun addFriend(@Body uid: String): Call<Void>

    @DELETE("friend")
    fun deleteFriend(@Body uid: String): Call<Void>
}