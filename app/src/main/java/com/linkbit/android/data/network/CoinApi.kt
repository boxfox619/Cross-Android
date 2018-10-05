package com.linkbit.android.data.network

import retrofit2.http.GET
import com.linkbit.android.data.model.coin.CoinNetworkObject
import com.linkbit.android.data.model.coin.CoinPriceNetworkObject
import io.reactivex.Single
import retrofit2.http.*


interface CoinApi {
    @GET("/coin/supported/list")
    fun getSupportedCoins(): Single<List<CoinNetworkObject>>

    @GET("coin/price")
    fun getPrice(@Query("symbol") symbol: String, @Query("locale") locale: String): Single<CoinPriceNetworkObject>
}