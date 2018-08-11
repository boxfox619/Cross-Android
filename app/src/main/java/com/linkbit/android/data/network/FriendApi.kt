package com.linkbit.android.data.network

import retrofit2.http.DELETE
import retrofit2.http.PUT
import com.linkbit.android.data.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.*


interface FriendApi{

    @GET("search/account/:type")
    fun searchFriends(@Query("text") text: String): Call<List<User>>

    @GET("friend")
    fun friendList(): Call<List<User>>

    @PUT("friend")
    fun addFriend(@Body uid: String): Call<Void>

    @DELETE("friend")
    fun deleteFriend(@Body uid: String): Call<Void>
}