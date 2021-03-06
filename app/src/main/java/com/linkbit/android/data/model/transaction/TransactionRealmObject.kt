package com.linkbit.android.data.model.transaction

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class TransactionRealmObject : RealmObject(){
    @PrimaryKey
    @SerializedName("transactionHash") lateinit var transactionHash: String
    @SerializedName("sourceAddress") lateinit var sourceAddress: String
    @SerializedName("targetAddress") lateinit var targetAddress: String
    @SerializedName("symbol") lateinit var symbol: String
    @SerializedName("status") var status: Boolean = false
    @SerializedName("amount") var amount: Double = 0.toDouble()
    @SerializedName("targetProfile") lateinit var targetProfile: String
    @SerializedName("date") lateinit var date: String
    @SerializedName("blockNumber")  var blockNumber: Int = 0
    @SerializedName("confirmation") var confirmation: Int = 0
}