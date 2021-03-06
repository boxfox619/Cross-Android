package com.linkbit.android.data.network

import com.linkbit.android.data.model.transaction.TransactionResult
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.POST


interface WithdrawApi{
    @POST("withdraw")
    fun withdraw(@Field("symbol") symbol: String,
                 @Field("walletName") walletName: String,
                 @Field("walletData") walletData: String,
                 @Field("password") password: String,
                 @Field("targetAddress") targetAddress: String,
                 @Field("amount") amount: String): Single<TransactionResult>
}