package com.linkbit.android.data.model.coin

import com.linkbit.android.data.model.NetworkEntityMapper
import com.linkbit.android.data.model.user.UserNetworkObject
import com.linkbit.android.data.model.wallet.WalletNetworkObject
import com.linkbit.android.entity.UserModel
import com.linkbit.android.entity.WalletModel

object WalletNetworkEntityMapper : NetworkEntityMapper<WalletModel, WalletNetworkObject>{
    override fun fromNetworkObject(obj: WalletNetworkObject): WalletModel {
        return WalletModel().apply {
            ownerId = obj.ownerId
            ownerName = obj.ownerName
            walletName = obj.walletName
            coinSymbol = obj.coinSymbol
            description = obj.description
            originalAddress = obj.originalAddress
            linkbitAddress = obj.linkbitAddress
            balance = obj.balance
        }
    }

    override fun toNetworkObject(model: WalletModel): WalletNetworkObject {
        return WalletNetworkObject().apply {
            ownerId = model.ownerId
            ownerName = model.ownerName
            walletName = model.walletName
            coinSymbol = model.coinSymbol
            description = model.description
            originalAddress = model.originalAddress
            linkbitAddress = model.linkbitAddress
            balance = model.balance
        }
    }

}