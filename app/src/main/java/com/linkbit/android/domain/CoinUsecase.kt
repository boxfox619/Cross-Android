package com.linkbit.android.domain

import com.linkbit.android.entity.CoinModel
import io.reactivex.Observable
import io.reactivex.Single
import java.util.*

interface CoinUsecase : Repository {
    fun loadAllCoinList() : Single<List<CoinModel>>
    fun getSupportCoins() : Observable<List<CoinModel>>
    fun getCoinByName(name: String) : Single<CoinModel>
    fun getCoinBySymbol(symbol: String) : Single<CoinModel>
    fun getCoinIcon(coin: CoinModel) : Single<String>
    fun getCoinPrice(coin: CoinModel, locale: Locale) : Single<Double>
}