package com.linkbit.android.data.network

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.FormUrlEncoded
import com.linkbit.android.data.model.wallet.WalletData
import com.linkbit.android.data.model.coin.CoinNetworkObject
import com.linkbit.android.data.model.coin.CoinPriceNetworkObject
import com.linkbit.android.data.model.transaction.TransactionNetworkObject
import com.linkbit.android.data.model.wallet.WalletNetworkObject
import retrofit2.Call
import retrofit2.http.*


interface AuthApi{
    @GET("signin/")
    fun signin(): Call<SigninNetworkObject>

    @GET("logout/")
    fun logout(): Call<Void>
}