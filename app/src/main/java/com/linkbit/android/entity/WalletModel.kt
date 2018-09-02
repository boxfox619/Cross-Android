package com.linkbit.android.entity


open class WalletModel {
    open lateinit var  ownerId: String
    open lateinit var  ownerName: String
    open lateinit var  walletName: String
    open lateinit var  coinSymbol: String
    open lateinit var  description: String
    open lateinit var  accountAddress: String
    open lateinit var  linkbitAddress: String
    open var balance: Double = 0.0
}