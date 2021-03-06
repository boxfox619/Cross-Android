package com.linkbit.android.data.model;

class CoinStatistic {
    var symbol : String
        get() = field
    var money : String
        get() = field
    var name : String
        get() = field
    var balance : Double
        get() = field
    var price : Double
        get() = field

    constructor(symbol: String, money: String, name : String, balance: Double, price: Double){
        this.symbol = symbol
        this.money = money
        this.name = name
        this.balance = balance
        this.price = price
    }

}
