package com.linkbit.android.presentation.wallet.create

import com.linkbit.android.entity.TransactionModel
import com.linkbit.android.presentation.View

interface WithdrawView : View {
    fun setStep(step: Int)
    fun nextButtonEnabled(state: Boolean)
    fun setProgressDialogVisible(visible: Boolean)
    fun finishWithdraw(transaction: TransactionModel?)
}