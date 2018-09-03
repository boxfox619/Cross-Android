package com.linkbit.android.data.model.coin

import com.linkbit.android.data.model.RealmEntityMapper
import com.linkbit.android.data.model.wallet.WalletCreateNetworkObject
import com.linkbit.android.data.model.wallet.WalletDataRealmObject
import com.linkbit.android.data.model.wallet.WalletNetworkObject
import com.linkbit.android.data.model.wallet.WalletRealmObject
import com.linkbit.android.entity.WalletModel

object WalletRealmEntityMapper : RealmEntityMapper<WalletModel, WalletRealmObject>{
    override fun fromRealmObject(obj: WalletRealmObject): WalletModel {
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

    override fun toRealmObject(model: WalletModel): WalletRealmObject {
        return WalletRealmObject().apply {
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

    fun fromWalletCreated(obj: WalletCreateNetworkObject): WalletRealmObject {
        return WalletRealmObject().apply {
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

}