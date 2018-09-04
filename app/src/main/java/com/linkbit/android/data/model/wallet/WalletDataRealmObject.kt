package com.linkbit.android.data.model.wallet

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class WalletDataRealmObject : RealmObject(){
    @PrimaryKey
    @SerializedName("accountAddress")
    lateinit var accountAddress: String

    @SerializedName("walletFileName")
    lateinit var walletFileName: String

    @SerializedName("walletData")
    lateinit var walletData: String
}