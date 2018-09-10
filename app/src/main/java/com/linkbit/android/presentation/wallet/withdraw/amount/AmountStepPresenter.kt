package com.linkbit.android.presentation.wallet.list

import com.linkbit.android.R
import com.linkbit.android.presentation.Presenter

class AmountStepPresenter(
        view: AmountStepView,
        private val onAmountEntered: (amount: Double) -> Unit
) : Presenter<AmountStepView>(view) {
    //@TODO Current balance and amount validation

    fun setAmount(amount: String){
        val amountVal = amount.toDouble()

        onAmountEntered(amountVal)
    }

    fun init(){
        view.addTabSpec("tab1","address", R.drawable.ic_wallet_white_24dp)
        view.addTabSpec("tab2","friend", R.drawable.ic_wallet_white_24dp)
    }

}