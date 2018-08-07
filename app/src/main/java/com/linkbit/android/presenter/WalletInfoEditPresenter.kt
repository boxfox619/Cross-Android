package com.linkbit.android.presenter

import com.linkbit.android.model.WalletEditModel
import com.linkbit.android.ui.view.WalletInfoEditView


class WalletInfoEditPresenter (wallet: WalletEditModel, canNext : (state:Boolean)->Unit) : BasePresenter<WalletInfoEditView> {

    lateinit var view: WalletInfoEditView
    val wallet: WalletEditModel = wallet
    var passwordConfirm: String = ""
    val isValid: (state:Boolean) -> Unit = canNext

    fun setPassword(password: String): Boolean{
        wallet.password = password
        checkFormValidation()
        return true
    }

    fun setPasswordConfim(password: String): Boolean{
        val equal = this.wallet.password.equals(password)
        passwordConfirm = password
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
        var vaild = false
        if(wallet.password.equals(passwordConfirm)&&wallet.name.length > 0){
            vaild = true
        }
        isValid(vaild)
    }

    override fun addView(view: WalletInfoEditView) {
        this.view = view
        this.view.initView(this.wallet)
    }
    override fun removeView() {
        this.view == null
    }
}

