package com.linkbit.android.model;

class CoinStatistic {
    var symbol : String
        get() = field
    var money : String
        get() = field
    var name : String
        get() = field
    var balance : Double
        get() = field
    var price : Long
        get() = field

    constructor(symbol: String, money: String, name : String, balance: Double, price: Long){
        this.symbol = symbol
        this.money = money
        this.name = name
        this.balance = balance
        this.price = price
    }

}
