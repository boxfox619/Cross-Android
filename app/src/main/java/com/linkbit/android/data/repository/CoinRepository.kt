package com.linkbit.android.data.repository

import android.content.Context
import android.util.Log
import com.linkbit.android.R
import com.linkbit.android.data.model.coin.*
import com.linkbit.android.data.network.retrofit
import com.linkbit.android.entity.CoinModel
import com.linkbit.android.domain.CoinUsecase
import com.linkbit.android.entity.CoinPriceModel
import com.linkbit.android.helper.realm
import io.reactivex.Single
import java.util.*

class CoinRepository(private val context: Context) : CoinUsecase {

    override fun loadAllCoinList(): Single<List<CoinModel>> {
        Log.d("Networking", "try getting supported coin list")
        val single: Single<List<CoinModel>> = context.retrofit.coinAPI
                .getSupportedCoins()
                .map<List<CoinModel>> { it.map { CoinNetworkEntityMapper.fromNetworkObject(it) } }
        single.subscribe({ supportedCoinList ->
            if (supportedCoinList != null) {
                context.realm.beginTransaction()
                context.realm.where(CoinRealmObject::class.java).findAll().deleteAllFromRealm()
                context.realm.copyToRealm(supportedCoinList.map { CoinRealmEntityMapper.toRealmObject(it) })
                context.realm.commitTransaction()
            }
        }, {
            Log.d("Networking", "Supported coin list load fail")
            Log.d("Networking", it.message)
        })
        return single
    }

    override fun getSupportCoins(): Single<List<CoinModel>> {
        return Single.create { obs ->
            val result = context.realm.where(CoinRealmObject::class.java).findAll()
            if (result != null) {
                obs.onSuccess(result.map { CoinRealmEntityMapper.fromRealmObject(it) })
            }
        }
    }

    override fun getCoinByName(name: String): Single<CoinModel> {
        return Single.create { obs ->
            val coinObject = context.realm.where(CoinRealmObject::class.java).equalTo("name", name).findFirst()
            if (coinObject != null)
                obs.onSuccess(CoinRealmEntityMapper.fromRealmObject(coinObject))
            else
                obs.onError(Throwable())
        }
    }

    override fun getCoinBySymbol(symbol: String): Single<CoinModel> {
        return Single.create { obs ->
            val coinObject = context.realm.where(CoinRealmObject::class.java).equalTo("symbol", symbol).findFirst()
            if (coinObject != null)
                obs.onSuccess(CoinRealmEntityMapper.fromRealmObject(coinObject))
            else
                obs.onError(Throwable())
        }
    }

    override fun getCoinIconUrl(symbol: String): String {
        return context.getString(R.string.server_host) + context.getString(R.string.url_asset) + symbol.toUpperCase() + ".png"
    }

    override fun getCoinPrice(symbol: String, locale: Locale): Single<CoinPriceModel> {
        return context.retrofit.coinAPI.getPrice(symbol, locale.displayName)
                .map<CoinPriceModel> { price ->
                    CoinPriceModel().apply {
                        this.symbol = symbol
                        this.amount = price.amount
                        this.unit = price.unit
                    }
                }
    }
}