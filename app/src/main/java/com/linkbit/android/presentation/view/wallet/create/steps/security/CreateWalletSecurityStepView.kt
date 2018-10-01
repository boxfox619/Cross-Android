package com.linkbit.android.presentation.view.wallet.list

import com.linkbit.android.presentation.base.View

interface CreateWalletSecurityStepView : View {
    fun setPasswordInputError(msg: String?): Unit
    fun setPasswordConfirmInputError(msg: String?): Unit
}