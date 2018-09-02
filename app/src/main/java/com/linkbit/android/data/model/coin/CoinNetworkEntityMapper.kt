package com.linkbit.android.data.model.coin

import com.linkbit.android.data.model.NetworkEntityMapper
import com.linkbit.android.entity.CoinModel

object CoinNetworkEntityMapper : NetworkEntityMapper<CoinModel, CoinNetworkObject>{

    override fun toNetworkObject(model: CoinModel): CoinNetworkObject {
        return CoinNetworkObject().apply {
        }
    }

    override fun fromNetworkObject(obj: CoinNetworkObject): CoinModel {
        return CoinModel().apply {
            symbol = obj.symbol
            name = obj.name
        }
    }

}