package com.linkbit.android.presenter

import com.linkbit.android.model.Coin
import com.linkbit.android.model.Wallet
import com.linkbit.android.model.WalletEditModel
import com.linkbit.android.ui.view.CreateWalletView


class CreateWalletPresenter : BasePresenter<CreateWalletView> {

    lateinit var view: CreateWalletView
    lateinit var wallet: WalletEditModel
    var step: Int = 0

    fun baseCoinUpdate(item: Coin) {
        wallet.coin = item
    }

    fun subCoinUpdate(item: Coin) {
        val subCoinList = wallet.subCoinList
        for ((idx, it) in subCoinList.withIndex()) {
            if (it.symbol.equals(item.symbol)) {
                subCoinList.removeAt(idx)
                return
            }
        }
        subCoinList.add(item)
    }

    fun walletUpdate(wallet: WalletEditModel) {
        this.wallet = wallet
    }

    fun canNext(state: Boolean){

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

