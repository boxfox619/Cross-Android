package com.linkbit.android.data.model.coin

import com.google.gson.annotations.SerializedName

class CoinPriceNetworkObject {
    @SerializedName("amount") var amount: Double = 0.0
    @SerializedName("unit") lateinit var unit: String
}