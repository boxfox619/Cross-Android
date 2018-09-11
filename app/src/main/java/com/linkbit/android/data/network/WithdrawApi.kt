package com.linkbit.android.data.network

import com.linkbit.android.data.model.transaction.TransactionResult
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.POST


interface WithdrawApi{
    @POST("remittance")
    fun withdraw(@Field("symbol") symbol: String,
                 @Field("walletName") walletName: String,
                 @Field("walletData") walletData: String,
                 @Field("password") password: String,
                 @Field("targetAddress") targetAddress: String,
                 @Field("amount") amount: String): Call<TransactionResult>
}