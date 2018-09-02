package com.linkbit.android.data.model.wallet

import com.linkbit.android.entity.CoinModel


class WalletEditModel {
    var name: String = ""
    lateinit var coin: CoinModel //change symbol string to coin
    var description: String = ""
    var password: String = ""

}