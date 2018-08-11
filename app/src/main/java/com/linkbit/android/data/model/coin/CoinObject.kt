package com.linkbit.android.data.model.coin

import com.google.gson.annotations.SerializedName

open class CoinObject{
    @SerializedName("symbol") open lateinit var symbol : String
    @SerializedName("name") open lateinit var name : String
}