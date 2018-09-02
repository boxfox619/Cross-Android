package com.linkbit.android.data.model.wallet

import com.google.gson.annotations.SerializedName

class WalletCreateNetworkObject : WalletNetworkObject() {

    @SerializedName("walletFileName")
    lateinit var walletFileName: String

    @SerializedName("walletData")
    lateinit var walletData: String
}