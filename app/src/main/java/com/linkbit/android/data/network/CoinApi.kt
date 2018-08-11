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


interface CoinApi{
    @GET("coin/support/list")
    fun getSupportedCoins(): Call<List<CoinNetworkObject>>

    @GET("coin/price")
    fun getPrice(@Query("symbol") symbol: String, @Query("locale") locale: String): Call<Double>
}