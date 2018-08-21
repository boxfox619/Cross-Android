package com.linkbit.android.data.model.coin

import com.google.gson.annotations.SerializedName

class CoinNetworkObject {
    @SerializedName("symbol")
    lateinit var symbol: String
    @SerializedName("name")
    lateinit var name: String
}
