package com.linkbit.android.data.network

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.FormUrlEncoded
import com.linkbit.android.data.model.wallet.WalletData
import com.linkbit.android.data.model.transaction.TransactionNetworkObject
import com.linkbit.android.data.model.wallet.WalletNetworkObject
import retrofit2.Call
import retrofit2.http.*


interface WalletApi{

    @FormUrlEncoded
    @POST("wallet/")
    fun createWallet(@Path("symbol") symbol: String, @Field("name") name: String, @Field("description") description: String, @Field("password") password: String, @Field("major") major: Boolean, @Field("visible") visible: Boolean): Call<WalletData>

    @GET("wallet/list")
    fun getWalletList(): Call<List<WalletNetworkObject>>

    @GET("wallet/balance")
    fun getBalance(@Query("address") address: String): Call<String>

    @GET("wallet/{symbol}/price/all")
    fun getTotalPrice(@Path("symbol") symbol: String): Call<String>

    @GET("wallet/{symbol}/balance/all")
    fun getTotalBalance(@Path("symbol") symbol: String): Call<String>

    @GET("walletModel")
    fun getWalletInfo(@Query("address") address: String): Call<WalletNetworkObject>

    @GET("wallet/{symbol}/transaction")
    fun getTransactionStatus(@Path("symbol") symbol: String, @Query("hash") hash: String): Call<TransactionNetworkObject>

    @GET("wallet/{symbol}/transaction/count")
    fun getTransactionCount(@Path("symbol") symbol: String, @Query("address") address: String): Call<Int>

    @GET("wallet/transaction/list")
    fun addressTransactionList(@Query("address") address: String): Call<List<TransactionNetworkObject>>

    @GET("transaction/list")
    fun transactionList(): Call<List<TransactionNetworkObject>>
}