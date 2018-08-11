package com.linkbit.android.model.coin

import com.linkbit.android.model.EntityMapper
import io.reactivex.Observable


class GetCoins()

interface CoinRepository : Repository {
    fun getSupportCoins() : Observable<List<CoinModel>>
    fun getCoinByName() : Observable<CoinModel>
    fun getCoinBySymbol() : Observable<CoinModel>
    fun getCoinIcon() : Observable<String>
}

object CoinEntityMapper : EntityMapper<CoinModel, CoinObject>{

    override fun toRealmObject(model: CoinModel): CoinObject {
        return CoinObject().apply {
            symbol = model.symbol
            name = model.name
        }
    }

    override fun fronRealmObject(obj: CoinObject): CoinModel {
        return CoinModel(obj.symbol, obj.name)
    }

}