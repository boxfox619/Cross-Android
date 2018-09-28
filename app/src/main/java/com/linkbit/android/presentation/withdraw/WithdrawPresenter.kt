package com.linkbit.android.presentation.wallet.create

import com.linkbit.android.data.repository.WithdrawRepository
import com.linkbit.android.entity.TransactionModel
import com.linkbit.android.entity.WalletModel
import com.linkbit.android.helper.ToastHelper
import com.linkbit.android.presentation.Presenter


class WithdrawPresenter(
        view: WithdrawView,
        private val withdrawRepository: WithdrawRepository = WithdrawRepository(view.getContext())
) : Presenter<WithdrawView>(view) {
    var sourceWallet: WalletModel? = null
    var targetWallet: WalletModel? = null
    var amount: Double = 0.toDouble()
    var remainBalance: Double = 0.toDouble()
    lateinit var password: String
    lateinit var transactionResult: TransactionModel
    var step: Int = -1
    var canNext: Boolean = false

    fun canNext(state: Boolean) {
        canNext = state
        view.nextButtonEnabled(state)
    }

    fun sourceWalletSelected(wallet: WalletModel?) {
        this.sourceWallet = wallet
        this.canNext(this.sourceWallet != null)
    }

    fun targetWalletSelected(wallet: WalletModel?) {
        this.targetWallet = wallet
        this.canNext(this.targetWallet != null)
    }

    fun amountSelected(amount: Double) {
        this.amount = amount
        this.canNext(this.amount > 0)
    }

    fun onNext() {
        step += 1
        view.setStep(step)
        this.canNext(false)
    }

    fun init() {
    }

    fun doWithdraw() {
        this.view.setProgressDialogVisible(true)
        this.withdrawRepository.withdraw(this.sourceWallet!!.coinSymbol, this.sourceWallet!!.accountAddress, this.password, this.targetWallet!!.accountAddress, this.amount).subscribe({
            this.transactionResult = it
            // this.remainBalance
            // @TODO cal remain balance and binding this.remainBalance
            this.view.setProgressDialogVisible(false)
            onNext()
        }, {
            this.view.setProgressDialogVisible(false)
            ToastHelper.showToast(ctx, it.message!!)
            this.view.finishWithdraw(null)
        })
    }
}

