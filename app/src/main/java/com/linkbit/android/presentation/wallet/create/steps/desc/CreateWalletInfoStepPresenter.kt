package com.linkbit.android.presentation.wallet.list

import com.linkbit.android.data.model.wallet.WalletEditModel
import com.linkbit.android.presentation.Presenter

class CreateWalletInfoStepPresenter(
        view: CreateWalletInfoStepView,
        private val walletModel: WalletEditModel,
        private val isValid : (valid: Boolean) -> Unit
) : Presenter<CreateWalletInfoStepView>(view) {

    fun setName(name: String){
        walletModel.name = name
        notifyValid()
    }

    fun setDescription(desc: String){
        walletModel.description = desc
        notifyValid()
    }

    fun notifyValid(){
        this.isValid(walletModel.name.isEmpty())
    }
}