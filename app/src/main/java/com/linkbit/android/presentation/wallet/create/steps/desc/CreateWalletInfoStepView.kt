package com.linkbit.android.presentation.wallet.list

import com.linkbit.android.presentation.View

interface CreateWalletInfoStepView : View {
    fun setNameInputError(msg: String?): Unit
}