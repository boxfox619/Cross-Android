package com.linkbit.android.data.model.coin

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


class CoinRealmObject : RealmObject() {
    @PrimaryKey
    @SerializedName("symbol") open lateinit var symbol: String
    @SerializedName("name") open lateinit var name: String
}