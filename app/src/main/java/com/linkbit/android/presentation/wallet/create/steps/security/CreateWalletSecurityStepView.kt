package com.linkbit.android.presentation.wallet.list

import com.linkbit.android.presentation.View

interface CreateWalletSecurityStepView : View {
    fun setPasswordInputError(msg: String?): Unit
    fun setPasswordConfirmInputError(msg: String?): Unit
}