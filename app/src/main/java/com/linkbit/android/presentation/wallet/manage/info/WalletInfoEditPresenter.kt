package com.linkbit.android.presentation.wallet.manage.info

import com.linkbit.android.data.model.wallet.WalletEditModel
import com.linkbit.android.presentation.Presenter


class WalletInfoEditPresenter (view: WalletInfoEditView, wallet: WalletEditModel, isValid : (state:Boolean)->Unit) : Presenter<WalletInfoEditView>(view) {

    val wallet: WalletEditModel = wallet
    var password: String = ""
    val isValid: (state:Boolean) -> Unit = isValid

    fun setPassword(password: String): Boolean{
        this.password = password
        checkFormValidation()
        return true
    }

    fun setPasswordConfim(password: String): Boolean{
        val equal = this.wallet.password.equals(password)
        if (this.password.equals(password) && this.password.length > 0) {
            wallet.password = password
        }else{
            wallet.password = ""
        }
        checkFormValidation()
        return equal
    }

    fun setName(name: String) {
        this.wallet.name = name
        checkFormValidation()
    }

    fun setDescription(desc: String) {
        this.wallet.description = desc
        checkFormValidation()
    }

    fun checkFormValidation(){
        var valid = false
        valid = valid && wallet.name.length > 0
        valid = valid && wallet.description != null
        valid = valid && wallet.password.length > 0
        isValid(valid)
    }

    fun init() {
        this.view.initView(this.wallet)
        isValid(false)
    }
}

