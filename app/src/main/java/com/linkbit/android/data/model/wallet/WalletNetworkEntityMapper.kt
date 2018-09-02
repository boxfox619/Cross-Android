package com.linkbit.android.data.model.coin

import com.linkbit.android.data.model.NetworkEntityMapper
import com.linkbit.android.data.model.user.UserNetworkObject
import com.linkbit.android.data.model.wallet.WalletCreateNetworkObject
import com.linkbit.android.data.model.wallet.WalletDataRealmObject
import com.linkbit.android.data.model.wallet.WalletNetworkObject
import com.linkbit.android.entity.UserModel
import com.linkbit.android.entity.WalletDataModel
import com.linkbit.android.entity.WalletModel

object WalletNetworkEntityMapper : NetworkEntityMapper<WalletModel, WalletNetworkObject>{
    override fun fromNetworkObject(obj: WalletNetworkObject): WalletModel {
        return WalletModel().apply {
            ownerId = obj.ownerId
            ownerName = obj.ownerName
            walletName = obj.walletName
            coinSymbol = obj.coinSymbol
            description = obj.description
            accountAddress = obj.accountAddress
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
            accountAddress = model.accountAddress
            linkbitAddress = model.linkbitAddress
            balance = model.balance
        }
    }

    fun fromWalletCreated(obj: WalletCreateNetworkObject): WalletModel {
        return WalletModel().apply{
            ownerId = obj.ownerId
            ownerName = obj.ownerName
            walletName = obj.walletName
            coinSymbol = obj.coinSymbol
            description = obj.description
            accountAddress = obj.accountAddress
            linkbitAddress = obj.linkbitAddress
            balance = obj.balance
        }
    }

    fun fromWalletCreatedToRealm(obj: WalletCreateNetworkObject): WalletDataRealmObject {
        return WalletDataRealmObject().apply {
            this.accountAddress = obj.accountAddress
            this.walletFileName = obj.walletFileName
            this.walletData = obj.walletData
        }
    }


}