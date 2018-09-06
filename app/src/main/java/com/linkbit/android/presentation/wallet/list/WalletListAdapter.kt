package com.linkbit.android.presentation.wallet.list

import android.content.Context
import android.view.ViewGroup
import com.linkbit.android.data.repository.CoinRepository
import com.linkbit.android.entity.WalletModel
import com.linkbit.android.ui.base.AbstractRecyclerAdapter
import java.util.*

class WalletListAdapter(
        context: Context,
        private val repository: CoinRepository = CoinRepository(context)
) : AbstractRecyclerAdapter<WalletModel, WalletCardViewHolder>(context) {
    override fun onItemViewType(position: Int): Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletCardViewHolder {
        return WalletCardViewHolder(context, parent)
    }

    override fun onBindViewHolder(holder: WalletCardViewHolder, position: Int) {
        var model = getItem(position)
        repository.getCoinPrice(model!!.coinSymbol, Locale.getDefault()).subscribe{
            val coinPrice = it
            holder.setName(model!!.walletName)
            holder.setSymbol(model!!.coinSymbol)
            holder.setMoney(coinPrice.unit)
            holder.setBalance(model!!.balance)
            holder.setExchangeBalance(model!!.balance * coinPrice.amount)
            holder.setCoinIcon(model!!.coinSymbol)
        }
    }
}
