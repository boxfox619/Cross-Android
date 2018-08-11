package com.linkbit.android.model

import com.linkbit.android.model.coin.CoinModel


class WalletEditModel {
    var name: String = ""
    lateinit var coin: CoinModel //change symbol string to coin
    var subCoinList: ArrayList<CoinModel> = ArrayList() //change symbol string to coin
    var description: String = ""
    var password: String = ""

}