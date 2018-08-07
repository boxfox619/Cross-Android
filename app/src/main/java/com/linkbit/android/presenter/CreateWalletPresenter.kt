package com.linkbit.android.presenter

import com.linkbit.android.model.WalletEditModel
import com.linkbit.android.ui.view.CreateWalletView


class CreateWalletPresenter : BasePresenter<CreateWalletView> {

    lateinit var view: CreateWalletView
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

    override fun addView(view: CreateWalletView) {
        this.view = view
        this.wallet = WalletEditModel()
    }

    override fun removeView() {
        this.view == null
    }
}

