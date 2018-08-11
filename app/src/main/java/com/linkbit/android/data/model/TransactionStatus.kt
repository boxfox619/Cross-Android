package com.linkbit.android.data.model

import com.linkbit.android.data.model.wallet.Wallet
import java.math.BigInteger

class TransactionStatus {
    var transactionHash: String? = null
    var sourceAddress: String? = null
    var targetAddress: String? = null
    var isVenefit: Boolean = false
    var status: Boolean = false
    var amount: Double = 0.toDouble()
    var targetWallet: Wallet? = null
    var targetProfile: User? = null
    var date: String? = null
    var blockNumber: BigInteger? = null
    var confirmation: Int = 0
}