package com.linkbit.android.data.network

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.POST


interface RemittanceApi{
    @POST("remittance")
    fun sendCoin(@Field("symbol") symbol: String,
                 @Field("walletName") walletName: String,
                 @Field("walletData") walletData: String,
                 @Field("password") password: String,
                 @Field("targetAddress") targetAddress: String,
                 @Field("amount") amount: String): Call<Void>
}