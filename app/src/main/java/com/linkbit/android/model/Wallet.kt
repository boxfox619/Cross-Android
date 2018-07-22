package com.linkbit.android.model


class Wallet {
    var uid: String? = null
    var owner: String? = null
    var ownerName: String? = null
    var name: String? = null
    lateinit var coin: Coin //change symbol string to coin
    var subCoinList: ArrayList<Coin> = ArrayList() //change symbol string to coin
    var coinName: String? = null
    var description: String? = null
    var originalAddress: String? = null
    var crossAddress: String? = null
    var balance: Double? = null
    var krBalance: Double = 0.toDouble()

}