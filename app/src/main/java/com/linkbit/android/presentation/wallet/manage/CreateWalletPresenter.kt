package com.linkbit.android.presentation.wallet.manage

import com.linkbit.android.model.WalletEditModel
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

    override fun destory() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

