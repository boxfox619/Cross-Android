package com.linkbit.android.presentation.wallet.manage

import com.linkbit.android.data.model.wallet.WalletEditModel
import com.linkbit.android.presentation.Presenter


class CreateWalletPresenter(view : CreateWalletView) : Presenter<CreateWalletView>(view) {

    lateinit var wallet: WalletEditModel
    var step: Int = 0
    var canNext: Boolean = false

    fun canNext(state: Boolean){
        canNext = state
        view.nextButtonEnabled(state)
    }

    fun onNext() {
        step += 1
        view.setStep(step)
    }

    fun init() {
        this.wallet = WalletEditModel()
    }
}

