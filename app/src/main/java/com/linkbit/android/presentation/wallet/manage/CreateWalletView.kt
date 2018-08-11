package com.linkbit.android.presentation.wallet.manage

import com.linkbit.android.presentation.View

interface CreateWalletView : View {
    fun setStep(step: Int)
    fun nextButtonEnabled(state: Boolean)
}