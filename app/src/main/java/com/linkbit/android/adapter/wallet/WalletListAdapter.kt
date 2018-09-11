package com.linkbit.android.adapter.wallet

import android.content.Context
import android.view.ViewGroup
import com.linkbit.android.adapter.SelectionMode
import com.linkbit.android.data.repository.CoinRepository
import com.linkbit.android.entity.WalletModel
import com.linkbit.android.ui.base.AbstractRecyclerAdapter
import java.util.*

class WalletListAdapter(
        context: Context,
        private val onSelectListener: ((wallet: WalletModel?) -> Unit)?,
        private val selectionMode: SelectionMode = SelectionMode.SINGLE,
        private val repository: CoinRepository = CoinRepository(context)
) : AbstractRecyclerAdapter<WalletModel, WalletCardViewHolder>(context) {
    override fun onItemViewType(position: Int): Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletCardViewHolder {
        return WalletCardViewHolder(context, parent)
    }

    override fun onBindViewHolder(holder: WalletCardViewHolder, position: Int) {
        var model = getItem(position)
        holder.init(model!!)

        onSelectListener?.let { holder.setOnClickListener { it(model) }}
        //@TODO Selection mode

        repository.getCoinPrice(model!!.coinSymbol, Locale.getDefault()).subscribe {
            val coinPrice = it
            holder.setMoney(coinPrice.unit)
            holder.setExchangeBalance(model!!.balance * coinPrice.amount)
        }
    }
}
