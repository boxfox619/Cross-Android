package com.linkbit.android.presenter

import com.linkbit.android.model.Coin
import com.linkbit.android.model.Wallet
import com.linkbit.android.ui.view.CreateWalletView


class CreateWalletPresenter : BasePresenter<CreateWalletView> {

    lateinit var createWalletView: CreateWalletView
    lateinit var wallet: Wallet

    fun baseCoinUpdate(item: Coin){
        wallet.coinSymbol = item.symbol
    }

    fun subCoinUpdate(item: Coin){
        val subCoinList = wallet.subCoinSymbolList
        if(subCoinList.contains(item.symbol)){
            subCoinList.removeAt(subCoinList.indexOf(item.symbol))
        }else{
            subCoinList.add(item.symbol)
        }
    }

    fun walletUpdate(wallet: Wallet){
        this.wallet = wallet
    }

    fun onNext(){
        // @TODO
    }

    override fun addView(view: CreateWalletView) {
        this.createWalletView = view
        this.wallet = Wallet()
    }

    override fun removeView() {
        this.createWalletView == null
    }
}

