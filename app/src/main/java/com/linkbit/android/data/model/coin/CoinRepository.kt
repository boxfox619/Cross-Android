package com.linkbit.android.data.model.coin

import com.linkbit.android.data.model.EntityMapper
import com.linkbit.android.data.repository.Repository
import io.reactivex.Observable

interface CoinRepository : Repository {
    fun getSupportCoins() : Observable<List<CoinModel>>
    fun getCoinByName(name: String) : Observable<CoinModel>
    fun getCoinBySymbol(symbol: String) : Observable<CoinModel>
    fun getCoinIcon(coin: CoinModel) : Observable<String>
}

object CoinEntityMapper : EntityMapper<CoinModel, CoinObject>{

    override fun toNetworkObject(model: CoinModel): CoinObject {
        return CoinObject().apply {
            symbol = model.symbol
            name = model.name
        }
    }

    override fun fromNetworkObject(obj: CoinObject): CoinModel {
        return CoinModel(obj.symbol, obj.name)
    }

}