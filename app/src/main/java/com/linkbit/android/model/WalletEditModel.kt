package com.linkbit.android.model


class WalletEditModel {
    var name: String = ""
    lateinit var coin: Coin //change symbol string to coin
    var subCoinList: ArrayList<Coin> = ArrayList() //change symbol string to coin
    var description: String = ""
    var password: String = ""

}