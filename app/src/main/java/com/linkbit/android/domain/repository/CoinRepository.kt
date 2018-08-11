package com.linkbit.android.domain.repository

import com.linkbit.android.entity.CoinModel
import io.reactivex.Observable
import io.reactivex.Single

interface CoinRepository : Repository {
    fun getSupportCoins() : Observable<List<CoinModel>>
    fun getCoinByName(name: String) : Single<CoinModel>
    fun getCoinBySymbol(symbol: String) : Single<CoinModel>
    fun getCoinIcon(coin: CoinModel) : Single<String>
}