package com.linkbit.android.data.model.coin

import com.linkbit.android.data.model.RealmEntityMapper
import com.linkbit.android.entity.CoinModel

object CoinRealmEntityMapper : RealmEntityMapper<CoinModel, CoinRealmObject> {
    override fun fromRealmObject(obj: CoinRealmObject): CoinModel {
        return CoinModel(obj.symbol, obj.name)
    }

    override fun toRealmObject(model: CoinModel): CoinRealmObject {
        return CoinRealmObject().apply {
            symbol = model.symbol
            name = model.name
        }
    }
}