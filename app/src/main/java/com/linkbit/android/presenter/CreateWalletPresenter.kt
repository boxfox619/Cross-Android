package com.linkbit.android.presenter

import com.linkbit.android.model.Coin
import com.linkbit.android.model.Wallet
import com.linkbit.android.ui.view.CreateWalletView


class CreateWalletPresenter : BasePresenter<CreateWalletView> {

    lateinit var view: CreateWalletView
    lateinit var wallet: Wallet
    var step: Int = 0

    fun baseCoinUpdate(item: Coin) {
        wallet.coinSymbol = item.symbol
    }

    fun subCoinUpdate(item: Coin) {
        val subCoinList = wallet.subCoinSymbolList
        if (subCoinList.contains(item.symbol)) {
            subCoinList.removeAt(subCoinList.indexOf(item.symbol))
        } else {
            subCoinList.add(item.symbol)
        }
    }

    fun walletUpdate(wallet: Wallet) {
        this.wallet = wallet
    }

    fun onNext() {
        step += 1
        view.setStep(step)
    }

    override fun addView(view: CreateWalletView) {
        this.view = view
        this.wallet = Wallet()
    }

    override fun removeView() {
        this.view == null
    }
}

