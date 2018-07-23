package com.linkbit.android.model


class WalletEditModel {
    var name: String? = null
    lateinit var coin: Coin //change symbol string to coin
    var subCoinList: ArrayList<Coin> = ArrayList() //change symbol string to coin
    var description: String? = null
    var password: String? = null

}