package com.linkbit.android.data.network

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.FormUrlEncoded
import com.linkbit.android.data.model.wallet.WalletCreateNetworkObject
import com.linkbit.android.data.model.wallet.WalletNetworkObject
import retrofit2.Call
import retrofit2.http.*


interface WalletApi{

    @FormUrlEncoded
    @POST("wallet/new")
    fun createWallet(@Field("symbol") symbol: String, @Field("name") name: String, @Field("description") description: String, @Field("password") password: String, @Field("major") major: Boolean, @Field("open") open: Boolean): Call<WalletCreateNetworkObject>

    @FormUrlEncoded
    @POST("wallet/add")
    fun addWallet(@Field("symbol") symbol: String, @Field("address") address: String, @Field("name") name: String, @Field("description") description: String, @Field("major") major: Boolean, @Field("open") open: Boolean): Call<WalletNetworkObject>

    @PUT("wallet")
    fun updateWallet(@Query("address") address: String, @Query("name") name: String, @Query("description") description: String, @Query("major") major: Boolean, @Query("open") open: Boolean): Call<Void>

    @GET("wallet")
    fun getWalletInfo(@Query("address") address: String): Call<WalletNetworkObject>

    @GET("wallet/list")
    fun getWalletList(): Call<List<WalletNetworkObject>>

    @GET("wallet/balance")
    fun getBalance(@Query("address") address: String): Call<String>

    @GET("wallet/price")
    fun getPrice(@Query("address") address: String): Call<String>

    @GET("wallet/price/all")
    fun getTotalPrice(@Query("symbol") symbol: String): Call<String>

    @GET("wallet/balance/all")
    fun getTotalBalance(@Query("symbol") symbol: String): Call<String>
}