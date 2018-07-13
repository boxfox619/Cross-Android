package com.linkbit.android.ui.view.header;

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.linkbit.android.R
import org.w3c.dom.Text
import java.text.DecimalFormat

class WalletCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    constructor(context: Context, parent: ViewGroup) : this(LayoutInflater.from(context).inflate(R.layout.view_wallet_card, parent, false))

    fun setName(name : String){
        itemView.findViewById<TextView>(R.id.tv_wallet_name).text = name
    }

    fun setSymbol(symbol: String){
        itemView.findViewById<TextView>(R.id.tv_wallet_symbol).text = symbol
    }

    fun setMoney(money: String){
        itemView.findViewById<TextView>(R.id.tv_wallet_money).text = money
    }

    fun setBalance(balance: Double){
        itemView.findViewById<TextView>(R.id.tv_wallet_balance).text = String.format("%.2f", balance)
    }

    fun setExchangeBalance(balance: Double){
        itemView.findViewById<TextView>(R.id.tv_wallet_exchange_balance).text = DecimalFormat("#,###").format(balance)
    }

    fun setCoinIcon(symbol: String){
        var url = String.format("%s/assets/%s.png", itemView.context.getString(R.string.server_host), symbol?.toUpperCase())
        Glide.with(itemView.context).load(url).into(itemView.findViewById(R.id.iv_wallet_icon))
    }
}
