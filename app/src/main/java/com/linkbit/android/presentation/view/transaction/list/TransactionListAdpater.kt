package com.linkbit.android.presentation.view.transaction.list

import android.content.Context
import android.view.ViewGroup
import com.linkbit.android.entity.TransactionModel
import com.linkbit.android.presentation.adapter.AbstractRecyclerAdapter

class TransactionListAdpater(context: Context) : AbstractRecyclerAdapter<TransactionModel, TransactionViewHolder>(context) {
    override fun onItemViewType(position: Int): Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        return TransactionViewHolder(context, parent)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        var model = getItem(position)
    }
}
