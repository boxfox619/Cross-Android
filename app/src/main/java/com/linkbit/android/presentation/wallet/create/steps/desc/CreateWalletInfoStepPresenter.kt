package com.linkbit.android.presentation.wallet.list

import com.linkbit.android.R
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

    private fun notifyValid(){
        val valid : Boolean = walletModel.name.isNotEmpty()
        if(!valid){
            this.view.setNameInputError(this.view.getContext().getString(R.string.err_create_wallet_et_name))
        }else{
            this.view.setNameInputError(null)
        }
        this.isValid(valid)
    }
}