package com.linkbit.android.data.network

import retrofit2.http.GET
import com.linkbit.android.data.model.transaction.TransactionNetworkObject
import retrofit2.Call
import retrofit2.http.Path
import retrofit2.http.Query


interface TransactionApi {

    @GET("transaction")
    fun getTransaction(@Query("txHash") txHash: String): Call<TransactionNetworkObject>

    @GET("transaction/list")
    fun getTransactionList(@Query("address") address: String, @Query("page") page: Int, @Query("count") count: Int): Call<List<TransactionNetworkObject>>

    @GET("transaction/count")
    fun getTransactionCount(@Query("address") address: String): Call<Integer>

    @GET("transaction/all/list")
    fun getIntegralTransactionList(@Query("page") page: Int, @Query("count") count: Int): Call<List<TransactionNetworkObject>>

    @GET("transaction/all/count")
    fun getTransactionCount(): Call<Int>

}