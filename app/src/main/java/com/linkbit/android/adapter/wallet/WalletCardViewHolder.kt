package com.linkbit.android.adapter.wallet;

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.linkbit.android.R
import com.linkbit.android.entity.WalletModel
import com.linkbit.android.helper.URLHelper
import kotlinx.android.synthetic.main.view_wallet_card.view.*
import java.text.DecimalFormat

class WalletCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    constructor(context: Context, parent: ViewGroup) : this(LayoutInflater.from(context).inflate(R.layout.view_wallet_card, parent, false))

    fun init(wallet: WalletModel){
        this.setName(wallet.walletName)
        this.setSymbol(wallet.coinSymbol)
        this.setBalance(wallet.balance)
        this.setCoinIcon(wallet.coinSymbol)
    }

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
        val url = URLHelper.createAssetUrl(itemView.context, symbol)
        Glide.with(itemView.context).load(url).into(itemView.findViewById(R.id.iv_wallet_icon))
    }

    fun setOnClickListener(listener: () -> Unit){
        itemView.view_wallet_card_root.setOnClickListener{listener()}
        for (i in 0 until itemView.view_wallet_card_root.childCount - 3 ) {
            itemView.view_wallet_card_root.getChildAt(i).setOnClickListener{listener()}
        }
    }
}
