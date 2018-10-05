package com.linkbit.android.domain

import com.linkbit.android.entity.CoinModel
import com.linkbit.android.entity.CoinPriceModel
import io.reactivex.Single
import java.util.*

interface CoinUsecase : Usecase {
    fun loadAllCoinList() : Single<List<CoinModel>>
    fun getSupportCoins() : Single<List<CoinModel>>
    fun getCoinByName(name: String) : Single<CoinModel>
    fun getCoinBySymbol(symbol: String) : Single<CoinModel>
    fun getCoinIconUrl(symbol: String) : String
    fun getCoinPrice(symbol: String, locale: Locale) : Single<CoinPriceModel>
}