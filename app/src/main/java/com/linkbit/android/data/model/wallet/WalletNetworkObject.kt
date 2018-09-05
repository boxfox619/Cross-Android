package com.linkbit.android.data.model.wallet

import com.google.gson.annotations.SerializedName

open class WalletNetworkObject {
    @SerializedName("ownerId") lateinit var  ownerId: String
    @SerializedName("ownerName") lateinit var  ownerName: String
    @SerializedName("walletName") lateinit var  walletName: String
    @SerializedName("coinSymbol") lateinit var  coinSymbol: String
    @SerializedName("description") lateinit var  description: String
    @SerializedName("accountAddress") lateinit var  accountAddress: String
    @SerializedName("linkbitAddress") lateinit var  linkbitAddress: String
    @SerializedName("balance") var balance: Double = 0.0
}
