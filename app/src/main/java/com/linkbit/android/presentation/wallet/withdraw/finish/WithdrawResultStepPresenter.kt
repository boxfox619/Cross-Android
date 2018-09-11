package com.linkbit.android.presentation.wallet.list

import com.linkbit.android.entity.TransactionModel
import com.linkbit.android.presentation.Presenter

class WithdrawResultStepPresenter(
        view: WithdrawResultStepView,
        private val remainBalance: Double,
        private val transactionResult: TransactionModel
) : Presenter<WithdrawResultStepView>(view) {
    fun init(){
        view.initWithdrawResult(transactionResult, remainBalance)
    }
}