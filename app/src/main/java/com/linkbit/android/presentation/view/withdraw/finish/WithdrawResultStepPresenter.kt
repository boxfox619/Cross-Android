package com.linkbit.android.presentation.view.wallet.list

import com.linkbit.android.entity.TransactionModel
import com.linkbit.android.presentation.base.Presenter

class WithdrawResultStepPresenter(
        view: WithdrawResultStepView,
        private val remainBalance: Double,
        private val transactionResult: TransactionModel,
        private val finishListener: () -> Unit
) : Presenter<WithdrawResultStepView>(view) {
    fun init(){
        view.initWithdrawResult(transactionResult, remainBalance)
    }
}