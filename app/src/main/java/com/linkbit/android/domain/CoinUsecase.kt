package com.linkbit.android.domain

import com.linkbit.android.entity.CoinModel
import com.linkbit.android.entity.CoinPriceModel
import rx.Single
import rx.Observable
import java.util.*

interface CoinUsecase : Repository {
    fun loadAllCoinList() : Single<List<CoinModel>>
    fun getSupportCoins() : Observable<List<CoinModel>>
    fun getCoinByName(name: String) : Single<CoinModel>
    fun getCoinBySymbol(symbol: String) : Single<CoinModel>
    fun getCoinIcon(symbol: String) : Single<String>
    fun getCoinPrice(symbol: String, locale: Locale) : Single<CoinPriceModel>
}