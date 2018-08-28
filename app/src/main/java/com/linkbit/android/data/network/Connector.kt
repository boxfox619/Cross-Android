package com.linkbit.android.data.network

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.linkbit.android.R
import com.linkbit.android.helper.Helper
import okhttp3.*
import okhttp3.logging.*
import retrofit2.*
import retrofit2.converter.gson.*
import rx.Single

/**
 * Created by root1 on 2017. 11. 23..
 */
class Connector(context: Context) {

    lateinit var walletAPI: WalletApi
    lateinit var friendAPI: FriendApi
    lateinit var coinAPI: CoinApi
    lateinit var transactionAPI: TransactionApi
    lateinit var authAPI: AuthApi

    init {
        getToken().subscribe{ token ->
        val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .addInterceptor { interceptor ->
                    val builder = interceptor.request().newBuilder()
                    builder.header("Authorization", token)
                    interceptor.proceed(builder.build())
                }
                .build()
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

    private fun getToken(): Single<String>{
        return Single.create{ subscriber ->
            val user = FirebaseAuth.getInstance().currentUser
            if(user !=null){
                val task = user.getIdToken(true).addOnCompleteListener { res ->
                    run {
                        if(res.isSuccessful){
                            subscriber.onSuccess(res.result.token)
                        }else{
                            subscriber.onSuccess("")
                        }
                    }
                }
            }else{
                subscriber.onSuccess("")
            }
        }
    }

}

val Context.retrofit: Connector
    get() = Connector(this)