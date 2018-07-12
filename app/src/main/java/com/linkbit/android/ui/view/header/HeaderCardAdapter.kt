package com.linkbit.android.ui.view.header;

import android.content.Context
import android.view.ViewGroup
import com.linkbit.android.ui.base.AbstractRecyclerAdapter

class HeaderCardAdapter(context: Context) : AbstractRecyclerAdapter<HeaderCardViewModel, HeaderCardViewHolder>(context) {
    override fun onItemViewType(position: Int): Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderCardViewHolder {
        return HeaderCardViewHolder(context, parent)
    }

    override fun onBindViewHolder(holder: HeaderCardViewHolder, position: Int) {
        var model = getItem(position)
        holder.setCoinName(model!!.name)
        holder.setCoinSymbol(model!!.symbol)
        holder.setCoinMoney(model!!.money)
        holder.setCoinBalance(model!!.balance)
        holder.setCoinPrice(model!!.price)
        holder.setCoinIcon(model!!.symbol)
    }
}
