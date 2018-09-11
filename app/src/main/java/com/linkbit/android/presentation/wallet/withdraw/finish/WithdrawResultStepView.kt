package com.linkbit.android.presentation.wallet.list

import com.linkbit.android.entity.TransactionModel
import com.linkbit.android.entity.WalletModel
import com.linkbit.android.presentation.View

interface WithdrawResultStepView : View {
    fun initWithdrawResult(result: TransactionModel, remainBalance: Double)
}