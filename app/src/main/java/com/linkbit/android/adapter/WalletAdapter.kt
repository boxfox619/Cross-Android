package com.linkbit.android.adapter

import android.content.Context
import android.view.ViewGroup
import com.linkbit.android.entity.WalletModel
import com.linkbit.android.ui.base.AbstractRecyclerAdapter
import com.linkbit.android.presentation.main.holder.WalletCardViewHolder

class WalletAdapter(context: Context) : AbstractRecyclerAdapter<WalletModel, WalletCardViewHolder>(context) {
    override fun onItemViewType(position: Int): Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletCardViewHolder {
        return WalletCardViewHolder(context, parent)
    }

    override fun onBindViewHolder(holder: WalletCardViewHolder, position: Int) {
        var model = getItem(position)
        holder.setName(model!!.name!!)
        holder.setSymbol(model!!.coin!!)
        holder.setMoney("KRW")
        holder.setBalance(model!!.balance!!)
        holder.setExchangeBalance(model!!.krBalance!!)
        holder.setCoinIcon(model!!.coin!!)
    }
}
