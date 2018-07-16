package com.linkbit.android.presenter

import com.linkbit.android.model.Wallet
import com.linkbit.android.ui.view.WalletInfoEditView


class WalletInfoEditPresenter : BasePresenter<WalletInfoEditView> {

    lateinit var view: WalletInfoEditView
    lateinit var wallet: Wallet

    fun setWalletName(name: String){
        wallet.name = name
    }

    fun setWalletDescription(desc: String){
        wallet.description = desc
    }

    fun setPassword(password: String){

    }

    fun setPasswordConfim(password: String){

    }

    override fun addView(view: WalletInfoEditView) {
        this.view = view
        this.wallet = Wallet()
    }
    override fun removeView() {
        this.view == null
    }
}

