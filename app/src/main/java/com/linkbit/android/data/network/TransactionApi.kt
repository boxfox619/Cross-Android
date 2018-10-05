package com.linkbit.android.data.network

import retrofit2.http.GET
import com.linkbit.android.data.model.transaction.TransactionNetworkObject
import io.reactivex.Single
import retrofit2.http.Query


interface TransactionApi {

    @GET("transaction")
    fun getTransaction(@Query("txHash") txHash: String): Single<TransactionNetworkObject>

    @GET("transaction/list")
    fun getTransactionList(@Query("address") address: String, @Query("page") page: Int, @Query("count") count: Int): Single<List<TransactionNetworkObject>>

    @GET("transaction/count")
    fun getTransactionCount(@Query("address") address: String): Single<Integer>

    @GET("transaction/all/list")
    fun getIntegralTransactionList(@Query("page") page: Int, @Query("count") count: Int): Single<List<TransactionNetworkObject>>

    @GET("transaction/all/count")
    fun getTransactionCount(): Single<Int>

}