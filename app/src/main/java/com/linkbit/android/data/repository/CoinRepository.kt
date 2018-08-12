package com.linkbit.android.data.repository

import android.content.Context
import com.linkbit.android.R
import com.linkbit.android.data.model.coin.*
import com.linkbit.android.data.network.Response
import com.linkbit.android.data.network.retrofit
import com.linkbit.android.entity.CoinModel
import com.linkbit.android.domain.CoinUsecase
import com.linkbit.android.entity.CoinPriceModel
import com.linkbit.android.util.realm
import io.reactivex.Observable
import io.reactivex.Single
import java.util.*

class CoinRepository(private val context: Context) : CoinUsecase {

    override fun loadAllCoinList(): Single<List<CoinModel>> {
        return Single.create { subscriber ->
            context.retrofit.coinAPI.getSupportedCoins().enqueue((object : Response<List<CoinNetworkObject>>(context) {
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

    override fun getSupportCoins(): Observable<List<CoinModel>> {
        return Observable.create { obs ->
            context.realm.where(CoinRealmObject::class.java).findAll().asObservable().subscribe {
                obs.onNext(it.map { CoinRealmEntityMapper.fromRealmObject(it) })
            }
        }
    }

    override fun getCoinByName(name: String): Single<CoinModel> {
        return Single.create { obs ->
            val coinObject = context.realm.where(CoinRealmObject::class.java).equalTo("name", name).findFirst()
            if (coinObject != null)
                obs.onSuccess(CoinRealmEntityMapper.fromRealmObject(coinObject))
            else
                obs.onError(null)
        }
    }

    override fun getCoinBySymbol(symbol: String): Single<CoinModel> {
        return Single.create { obs ->
            val coinObject = context.realm.where(CoinRealmObject::class.java).equalTo("symbol", symbol).findFirst()
            if (coinObject != null)
                obs.onSuccess(CoinRealmEntityMapper.fromRealmObject(coinObject))
            else
                obs.onError(null)
        }
    }

    override fun getCoinIcon(symbol: String): Single<String> {
        return Single.create { obs ->
            obs.onSuccess(context.getString(R.string.server_host) + context.getString(R.string.url_asset) + symbol.toUpperCase() + ".png")
        }
    }

    override fun getCoinPrice(symbol: String, locale: Locale): Single<CoinPriceModel> {
        return Single.create{ subscriber ->
            context.retrofit.coinAPI.getPrice(symbol, locale.displayName).enqueue((object : Response<CoinPriceNetworkObject>(context) {
                override fun setResponseData(code: Int, price: CoinPriceNetworkObject?) {
                    if (isSuccess(code) && price != null) {
                        subscriber.onSuccess(CoinPriceModel().apply {
                            this.symbol = symbol
                            this.amount = price.amount
                            this.unit = price.unit
                        })
                    } else {
                        subscriber.onError(null)
                    }
                }
            }))
        }
    }
}