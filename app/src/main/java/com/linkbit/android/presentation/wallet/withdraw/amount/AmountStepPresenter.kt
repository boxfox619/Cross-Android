package com.linkbit.android.presentation.wallet.list

import com.linkbit.android.presentation.Presenter

class AmountStepPresenter(
        view: AmountStepView,
        onAmountEntered: (amount: Double) -> Unit
) : Presenter<AmountStepView>(view) {
    //@TODO Current balance and amount validation

    fun setAmount(amount: String){
        val amountVal = amount.toInt()

    }

}