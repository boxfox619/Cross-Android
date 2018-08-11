package com.linkbit.android.entity

import java.math.BigInteger

class TransactionModel {
    lateinit var transactionHash: String
    lateinit var sourceAddress: String
    lateinit var targetAddress: String
    var status: Boolean = false
    var amount: Double = 0.toDouble()
    lateinit var targetProfile: String
    lateinit var date: String
    var blockNumber: Int = 0
    var confirmation: Int = 0
}