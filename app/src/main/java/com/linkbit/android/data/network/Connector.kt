package com.linkbit.android.data.network

import android.content.Context
import android.util.Log
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.linkbit.android.R
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
    val transactionAPI: TransactionApi
    val authAPI: AuthApi

    init {
        val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .addInterceptor{ interceptor ->
                    val builder = interceptor.request().newBuilder()
                    val user = FirebaseAuth.getInstance().getCurrentUser()
                    if (user != null) {
                        val task = user.getIdToken(true)
                        val tokenResult = Tasks.await(task)
                        val token = tokenResult.token
                        if (token != null) {
                            Log.d("Networking", "Token : "+token)
                            builder.header("Authorization", token)
                        }
                    }
                    interceptor.proceed(builder.build())
                }.build()
        val retrofit = Retrofit.Builder().client(client)
                .baseUrl(context.getString(R.string.server_host))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        walletAPI = retrofit.create(WalletApi::class.java)
        friendAPI = retrofit.create(FriendApi::class.java)
        coinAPI = retrofit.create(CoinApi::class.java)
        transactionAPI = retrofit.create(TransactionApi::class.java)
        authAPI = retrofit.create(AuthApi::class.java)
    }

}

val Context.retrofit: Connector
    get() = Connector(this)