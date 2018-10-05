package com.linkbit.android.presentation.adapter.wallet

import android.content.Context
import android.view.ViewGroup
import com.linkbit.android.R
import com.linkbit.android.presentation.adapter.SelectionMode
import com.linkbit.android.data.repository.CoinRepository
import com.linkbit.android.entity.WalletModel
import com.linkbit.android.helper.ToastHelper
import com.linkbit.android.presentation.adapter.AbstractRecyclerAdapter
import java.util.*

class WalletListAdapter(
        context: Context,
        private val onSelectListener: ((wallet: WalletModel) -> Unit)? = null,
        private val selectionMode: SelectionMode = SelectionMode.NONE,
        private val repository: CoinRepository = CoinRepository(context)
) : AbstractRecyclerAdapter<WalletModel, WalletCardViewHolder>(context) {
    override fun onItemViewType(position: Int): Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletCardViewHolder {
        return WalletCardViewHolder(context, parent)
    }

    override fun onBindViewHolder(holder: WalletCardViewHolder, position: Int) {
        var model = getItem(position)
            model!!.let { model ->
                holder.init(model)
                if (this.selectionMode != SelectionMode.NONE) {
                    onSelectListener?.let { listener ->
                        holder.setOnClickListener {
                            if (this.selectionMode == SelectionMode.SINGLE) {
                                this.clearSelectedItems()
                            }
                            this.addItem(model)
                            listener(model)
                        }
                    }
                }
            repository.getCoinPrice(model.coinSymbol, Locale.getDefault()).subscribe ({
                val coinPrice = it
                holder.setMoney(coinPrice.unit)
                holder.setExchangeBalance(model.balance * coinPrice.amount)
            }, {
                ToastHelper.showToast(context, R.string.err_wallet_list_load_fail)
                //@TODO coin price load error handling
            })
        }
    }
}
