package com.linkbit.android.presentation.view.wallet.list

import com.linkbit.android.entity.TransactionModel
import com.linkbit.android.presentation.base.View

interface WithdrawResultStepView : View {
    fun initWithdrawResult(result: TransactionModel, remainBalance: Double)
}