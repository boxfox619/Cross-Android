package com.linkbit.android.data.network.loader

import android.content.Context
import com.linkbit.android.data.model.coin.CoinNetworkEntityMapper
import com.linkbit.android.entity.CoinModel
import com.linkbit.android.data.network.Response
import com.linkbit.android.data.model.coin.CoinNetworkObject
import com.linkbit.android.data.network.retrofit
import com.linkbit.android.domain.loader.CoinLoader
import io.reactivex.Single

class CoinNetworkLoader(private val context: Context) : CoinLoader {
    override fun loadAllCoinList(): Single<List<CoinModel>> {
        return Single.create { subscriber ->
            context.retrofit.walletAPI.getSupportedCoins().enqueue((object : Response<List<CoinNetworkObject>>(context) {
                override fun setResponseData(code: Int, newWalletList: List<CoinNetworkObject>?) {
                    if (isSuccess(code) && newWalletList != null) {
                        val loadedCoinList: List<CoinModel> = newWalletList.map { it -> CoinNetworkEntityMapper.fromNetworkObject(it) }
                        subscriber.onSuccess(loadedCoinList)
                    } else {
                        subscriber.onError(null)
                    }
                }
            }))
        }
    }
}