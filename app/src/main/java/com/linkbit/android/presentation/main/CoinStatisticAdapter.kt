package com.linkbit.android.presentation.main;

import android.content.Context
import android.view.ViewGroup
import com.linkbit.android.data.model.CoinStatistic
import com.linkbit.android.ui.base.AbstractRecyclerAdapter
import com.linkbit.android.presentation.main.CoinStatisticCardViewHolder

class CoinStatisticAdapter(context: Context) : AbstractRecyclerAdapter<CoinStatistic, CoinStatisticCardViewHolder>(context) {
    override fun onItemViewType(position: Int): Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinStatisticCardViewHolder {
        return CoinStatisticCardViewHolder(context, parent)
    }

    override fun onBindViewHolder(holder: CoinStatisticCardViewHolder, position: Int) {
        var model = getItem(position)
        holder.setCoinName(model!!.name)
        holder.setCoinSymbol(model!!.symbol)
        holder.setCoinMoney(model!!.money)
        holder.setCoinBalance(model!!.balance)
        holder.setCoinPrice(model!!.price)
        holder.setCoinIcon(model!!.symbol)
    }
}
