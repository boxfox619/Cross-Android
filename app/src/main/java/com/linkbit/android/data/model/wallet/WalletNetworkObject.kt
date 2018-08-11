package com.linkbit.android.data.model.wallet

import com.google.gson.annotations.SerializedName

class WalletNetworkObject {
    @SerializedName("ownerId") lateinit var  ownerId: String
    @SerializedName("ownerName") lateinit var  ownerName: String
    @SerializedName("name") lateinit var  walletName: String
    @SerializedName("coin") lateinit var  coinSymbol: String
    @SerializedName("description") lateinit var  description: String
    @SerializedName("originalAddress") lateinit var  originalAddress: String
    @SerializedName("linkbitAddress") lateinit var  linkbitAddress: String
    @SerializedName("balance") var balance: Double = 0.0
}
