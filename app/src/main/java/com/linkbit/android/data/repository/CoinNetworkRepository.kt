package com.linkbit.android.data.repository

import android.content.Context
import com.google.gson.annotations.SerializedName
import com.linkbit.android.model.Wallet
import com.linkbit.android.model.coin.CoinModel
import com.linkbit.android.model.coin.CoinRepository
import com.linkbit.android.data.network.Response
import com.linkbit.android.data.network.retrofit
import io.reactivex.Observable

class CoinNetworkRepository(private val context: Context) : CoinRepository {

    override fun getSupportCoins(): Observable<List<CoinModel>> {
        return context.retrofit.walletAPI.getSupportedCoins().enqueue((object : Response<List<Wallet>>(ctx) {
            override fun setResponseData(code: Int, newWalletList: List<Wallet>?) {
                if (isSuccess(code)) {
                    walletList.onNext(newWalletList)
                    subscriber.onNext(newWalletList)
                    subscriber.onComplete()
                } else {
                    subscriber.onError(null)
                }
            }
        }))
    }

    override fun getCoinByName(): Observable<CoinModel> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCoinBySymbol(): Observable<CoinModel> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCoinIcon(): Observable<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

data class CoinObject {
    @SerializedName("symbol") var symbol: String? = null
    @SerializedName("name") var name: String? = null
} : Response