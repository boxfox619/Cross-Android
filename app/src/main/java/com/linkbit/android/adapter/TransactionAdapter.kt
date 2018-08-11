package com.linkbit.android.adapter

import android.content.Context
import android.view.ViewGroup
import com.linkbit.android.data.model.TransactionStatus
import com.linkbit.android.ui.base.AbstractRecyclerAdapter
import com.linkbit.android.presentation.main.holder.TransactionViewHolder

class TransactionAdapter(context: Context) : AbstractRecyclerAdapter<TransactionStatus, TransactionViewHolder>(context) {
    override fun onItemViewType(position: Int): Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        return TransactionViewHolder(context, parent)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        var model = getItem(position)
    }
}
