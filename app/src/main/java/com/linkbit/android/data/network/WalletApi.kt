package com.linkbit.android.data.network

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.FormUrlEncoded
import com.linkbit.android.data.model.wallet.WalletData
import com.linkbit.android.data.model.coin.CoinNetworkObject
import com.linkbit.android.data.model.transaction.TransactionNetworkObject
import com.linkbit.android.data.model.wallet.WalletNetworkObject
import retrofit2.Call
import retrofit2.http.*


interface WalletApi{
    @GET("support/walletModel/list")
    fun getSupportedCoins(): Call<List<CoinNetworkObject>>

    @FormUrlEncoded
    @POST("walletModel/")
    fun createWallet(@Path("symbol") symbol: String, @Field("name") name: String, @Field("description") description: String, @Field("password") password: String, @Field("major") major: Boolean, @Field("visible") visible: Boolean): Call<WalletData>

    @GET("walletModel/list")
    fun getWalletList(): Call<List<WalletNetworkObject>>

    @GET("walletModel/balance")
    fun getBalance(@Query("address") address: String): Call<String>

    @GET("walletModel/price")
    fun getPrice(@Query("address") address: String): Call<String>

    @GET("walletModel/{symbol}/price/all")
    fun getTotalPrice(@Path("symbol") symbol: String): Call<String>

    @GET("walletModel/{symbol}/balance/all")
    fun getTotalBalance(@Path("symbol") symbol: String): Call<String>

    @GET("walletModel")
    fun getWalletInfo(@Query("address") address: String): Call<WalletNetworkObject>

    @GET("walletModel/{symbol}/transaction")
    fun getTransactionStatus(@Path("symbol") symbol: String, @Query("hash") hash: String): Call<TransactionNetworkObject>

    @GET("walletModel/{symbol}/transaction/count")
    fun getTransactionCount(@Path("symbol") symbol: String, @Query("address") address: String): Call<Int>

    @GET("/walletModel/transaction/list")
    fun addressTransactionList(@Query("address") address: String): Call<List<TransactionNetworkObject>>

    @GET("/transaction/list")
    fun transactionList(): Call<List<TransactionNetworkObject>>
}