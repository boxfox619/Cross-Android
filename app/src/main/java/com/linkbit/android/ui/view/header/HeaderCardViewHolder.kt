package com.linkbit.android.ui.view.header;

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.linkbit.android.R
import java.text.DecimalFormat

class HeaderCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    constructor(context: Context, parent: ViewGroup) : this(LayoutInflater.from(context).inflate(R.layout.view_header_statistic_card, parent, false))

    fun setCoinName(name : String){
        itemView.findViewById<TextView>(R.id.tv_coin_name).text = name
    }

    fun setCoinSymbol(symbol : String){
        itemView.findViewById<TextView>(R.id.tv_coin_symbol).text = symbol
    }

    fun setCoinMoney(money: String){
        itemView.findViewById<TextView>(R.id.tv_coin_money).text = money
    }

    fun setCoinBalance(balance : Double){
        itemView.findViewById<TextView>(R.id.tv_statistic_balance).text = String.format("%.2f", balance)
    }

    fun setCoinPrice(price: Long){
        itemView.findViewById<TextView>(R.id.tv_statistic_price).text = DecimalFormat("#,###").format(price)
    }

    fun setCoinIcon(symbol: String){
        var url = String.format("%s/assets/%s.png", itemView.context.getString(R.string.host), symbol?.toUpperCase())
        Glide.with(itemView.context).load(url).into(itemView.findViewById(R.id.iv_statistic_icon))
    }
}
