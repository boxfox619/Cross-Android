package com.linkbit.android.adapter

import android.content.Context
import android.view.ViewGroup
import com.linkbit.android.model.TransactionStatus
import com.linkbit.android.model.User
import com.linkbit.android.model.Wallet
import com.linkbit.android.ui.base.AbstractRecyclerAdapter
import com.linkbit.android.ui.view.holder.FriendCardViewHolder
import com.linkbit.android.ui.view.holder.TransactionViewHolder
import com.linkbit.android.ui.view.holder.WalletCardViewHolder

class TransactionAdapter(context: Context) : AbstractRecyclerAdapter<TransactionStatus, TransactionViewHolder>(context) {
    override fun onItemViewType(position: Int): Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        return TransactionViewHolder(context, parent)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        var model = getItem(position)
    }
}
