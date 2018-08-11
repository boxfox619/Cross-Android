package com.linkbit.android.data.repository

import android.content.Context
import com.linkbit.android.R
import com.linkbit.android.data.model.coin.CoinRealmEntityMapper
import com.linkbit.android.data.model.coin.CoinRealmObject
import com.linkbit.android.entity.CoinModel
import com.linkbit.android.domain.repository.CoinRepository
import com.linkbit.android.util.realm
import io.reactivex.Observable
import io.reactivex.Single

class CoinRealmRepository(private val context: Context) : CoinRepository {

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

    override fun getCoinIcon(coin: CoinModel): Single<String> {
        return Single.create { obs ->
            obs.onSuccess(context.getString(R.string.server_host) + context.getString(R.string.url_asset) + coin.name.toUpperCase() + ".png")
        }
    }
}