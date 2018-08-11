package com.linkbit.android.data.model.coin

import com.linkbit.android.data.model.NetworkEntityMapper
import com.linkbit.android.entity.CoinModel

object CoinNetworkEntityMapper : NetworkEntityMapper<CoinModel, CoinNetworkObject>{

    override fun toNetworkObject(model: CoinModel): CoinNetworkObject {
        return CoinNetworkObject().apply {
            symbol = model.symbol
            name = model.name
        }
    }

    override fun fromNetworkObject(obj: CoinNetworkObject): CoinModel {
        return CoinModel(obj.symbol, obj.name)
    }

}