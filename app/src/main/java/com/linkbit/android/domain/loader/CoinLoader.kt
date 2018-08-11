package com.linkbit.android.domain.loader

import com.linkbit.android.entity.CoinModel
import io.reactivex.Single

interface CoinLoader: Loader {
    fun loadAllCoinList() : Single<List<CoinModel>>
}