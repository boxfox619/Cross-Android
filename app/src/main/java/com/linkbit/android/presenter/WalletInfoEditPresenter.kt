package com.linkbit.android.presenter

import com.linkbit.android.model.WalletEditModel
import com.linkbit.android.ui.view.WalletInfoEditView


class WalletInfoEditPresenter (wallet: WalletEditModel, confirmListener: (wallet: WalletEditModel) -> Unit) : BasePresenter<WalletInfoEditView> {

    lateinit var view: WalletInfoEditView
    val wallet: WalletEditModel = wallet
    private val confirmListener: (wallet: WalletEditModel) -> Unit = confirmListener

    fun setPassword(password: String): Boolean{
        wallet.password = password
        return true
    }

    fun setPasswordConfim(password: String): Boolean{
        return this.wallet.password.equals(password)
    }

    fun onFinish(name: String, desc: String){
        wallet.name = name
        wallet.description = desc
        confirmListener(wallet)
    }

    override fun addView(view: WalletInfoEditView) {
        this.view = view
        this.view.initView(this.wallet)
    }
    override fun removeView() {
        this.view == null
    }
}

