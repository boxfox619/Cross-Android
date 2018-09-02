package com.linkbit.android.data.model.wallet

import com.linkbit.android.data.model.RealmEntityMapper
import com.linkbit.android.entity.WalletDataModel

object WalletDataRealmEntityMapper : RealmEntityMapper<WalletDataModel, WalletDataRealmObject>{
    override fun fromRealmObject(obj: WalletDataRealmObject): WalletDataModel {
        return WalletDataModel().apply{
            this.accountAddress = obj.accountAddress
            this.walletFileName = obj.walletFileName
            this.walletData = obj.walletData
        }
    }

    override fun toRealmObject(model: WalletDataModel): WalletDataRealmObject {
        return WalletDataRealmObject().apply{
            this.accountAddress = model.accountAddress
            this.walletFileName = model.walletFileName
            this.walletData = model.walletData
        }
    }

    fun fromWalletCreatedToModel(obj: WalletCreateNetworkObject): WalletDataModel {
        return WalletDataModel().apply{
            this.accountAddress = obj.accountAddress
            this.walletFileName = obj.walletFileName
            this.walletData = obj.walletData
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