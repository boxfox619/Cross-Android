package com.linkbit.android.data.network

import com.linkbit.android.model.TransactionStatus
import retrofit2.http.GET
import com.linkbit.android.model.Wallet
import retrofit2.http.POST
import retrofit2.http.FormUrlEncoded
import com.linkbit.android.model.coin.CoinModel
import com.linkbit.android.model.WalletData
import retrofit2.Call
import retrofit2.http.*


interface WalletApi{
    @GET("support/wallet/list")
    fun getSupportedCoins(): Call<List<CoinModel>>

    @FormUrlEncoded
    @POST("wallet/")
    fun createWallet(@Path("symbol") symbol: String, @Field("name") name: String, @Field("description") description: String, @Field("password") password: String, @Field("major") major: Boolean, @Field("visible") visible: Boolean): Call<WalletData>

    @GET("wallet/list")
    fun getWalletList(): Call<List<Wallet>>

    @GET("wallet/balance")
    fun getBalance(@Query("address") address: String): Call<String>

    @GET("wallet/price")
    fun getPrice(@Query("address") address: String): Call<String>

    @GET("wallet/{symbol}/price/all")
    fun getTotalPrice(@Path("symbol") symbol: String): Call<String>

    @GET("wallet/{symbol}/balance/all")
    fun getTotalBalance(@Path("symbol") symbol: String): Call<String>

    @GET("wallet")
    fun getWalletInfo(@Query("address") address: String): Call<Wallet>

    @GET("wallet/{symbol}/transaction")
    fun getTransactionStatus(@Path("symbol") symbol: String, @Query("hash") hash: String): Call<TransactionStatus>

    @GET("wallet/{symbol}/transaction/count")
    fun getTransactionCount(@Path("symbol") symbol: String, @Query("address") address: String): Call<Int>

    @GET("/wallet/transaction/list")
    fun addressTransactionList(@Query("address") address: String): Call<List<TransactionStatus>>

    @GET("/transaction/list")
    fun transactionList(): Call<List<TransactionStatus>>
}