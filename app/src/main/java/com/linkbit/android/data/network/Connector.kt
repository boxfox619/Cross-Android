package com.linkbit.android.data.network

import android.content.Context
import com.linkbit.android.R
import com.linkbit.android.helper.Helper
import okhttp3.*
import okhttp3.logging.*
import retrofit2.*
import retrofit2.converter.gson.*

/**
 * Created by root1 on 2017. 11. 23..
 */
class Connector(context: Context) {

    val walletAPI: WalletApi
    val friendAPI: FriendApi
    val coinAPI: CoinApi

    init {
        val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .addInterceptor {
                    val token = Helper.getPrefData("token", context)
                    val builder = it.request().newBuilder()
                    builder.header("Authorization", token)
                    it.proceed(builder.build())
                }
                .build()
        val retrofit = Retrofit.Builder().client(client)
                .baseUrl(context.getString(R.string.server_host))
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        walletAPI = retrofit.create(WalletApi::class.java)
        friendAPI = retrofit.create(FriendApi::class.java)
        coinAPI = retrofit.create(CoinApi::class.java)
    }

}

val Context.retrofit: Connector
    get() = Connector(this)