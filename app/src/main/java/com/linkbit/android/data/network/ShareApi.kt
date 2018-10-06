package com.linkbit.android.data.network

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.FormUrlEncoded
import com.linkbit.android.data.model.wallet.WalletCreateNetworkObject
import com.linkbit.android.data.model.wallet.WalletNetworkObject
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*


interface ShareApi{

    @GET("share/qr")
    fun createWalletQrCode(@Query("address") address: String): Single<String>
}