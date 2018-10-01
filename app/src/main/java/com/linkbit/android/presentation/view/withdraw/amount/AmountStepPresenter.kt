package com.linkbit.android.presentation.view.wallet.list

import com.linkbit.android.R
import com.linkbit.android.data.repository.WalletRepository
import com.linkbit.android.entity.WalletModel
import com.linkbit.android.presentation.base.Presenter

class AmountStepPresenter(
        view: AmountStepView,
        private val sourceWallet: WalletModel,
        private val targetWallet: WalletModel,
        private val onAmountEntered: (amount: Double) -> Unit,
        private val walletRepository: WalletRepository = WalletRepository(view.getContext())
) : Presenter<AmountStepView>(view) {
    //@TODO Current balance and amount validation

    fun setAmount(amount: String) {
        val amountVal = amount.toDouble()

        onAmountEntered(amountVal)
    }

    fun init() {
        view.addTabSpec("tab1", "address", R.drawable.ic_wallet_white_24dp)
        view.addTabSpec("tab2", "friend", R.drawable.ic_wallet_white_24dp)
        view.setSoruceWalletInfo(sourceWallet)
        view.setTargetWalletInfo(targetWallet)
    }

}