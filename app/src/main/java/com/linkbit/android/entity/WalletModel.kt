package com.linkbit.android.entity


class WalletModel {
    lateinit var  ownerId: String
    lateinit var  ownerName: String
    lateinit var  walletName: String
    lateinit var  coinSymbol: String
    lateinit var  description: String
    lateinit var  originalAddress: String
    lateinit var  linkbitAddress: String
    var balance: Double = 0.0
}