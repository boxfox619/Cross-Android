package com.linkbit.android.presentation.trasnaction.list

import com.linkbit.android.entity.TransactionModel
import com.linkbit.android.presentation.base.View

interface TransactionListView : View {
    fun addTransationItems(items: List<TransactionModel>): Unit

}