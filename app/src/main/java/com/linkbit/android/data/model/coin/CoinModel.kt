package com.linkbit.android.data.model.coin

class CoinModel {
    constructor()
    constructor(symbol: String, name: String){
        this.symbol = symbol
        this.name = name
    }
    lateinit var symbol: String
    lateinit var name: String
}