package com.linkbit.android.presentation.wallet.list

import com.linkbit.android.R
import com.linkbit.android.data.model.wallet.WalletEditModel
import com.linkbit.android.presentation.Presenter

class CreateWalletSecurityStepPresenter(
        view: CreateWalletSecurityStepView,
        private val walletModel: WalletEditModel,
        private val isValid : (valid: Boolean) -> Unit
) : Presenter<CreateWalletSecurityStepView>(view) {
    private var password: String = ""
    private var passwordConfirm: String = ""

    fun setPassword(password: String){
        if(password.isNotEmpty() && password.length > MIN_PIN_LENGTH){
            this.password = password
        }else{
            this.view.setPasswordInputError(this.view.getContext().getString(R.string.err_create_wallet_et_password))
        }
        notifyValid()
    }

    fun setPasswordConfirm(passwordConfirm: String){
        if(passwordConfirm.isNotEmpty() && password == passwordConfirm){
            this.passwordConfirm = passwordConfirm
        }else{
            this.view.setPasswordConfirmInputError(this.view.getContext().getString(R.string.err_create_wallet_et_password_confirm))
        }
        notifyValid()
    }

    private fun notifyValid() {
        val valid: Boolean = password.length > MIN_PIN_LENGTH && password == passwordConfirm
        if (valid) {
            walletModel.password = password
        }
        this.isValid(valid)
    }

    companion object {
        private const val MIN_PIN_LENGTH : Int = 4
    }
}