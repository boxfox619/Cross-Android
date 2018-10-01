package com.linkbit.android.presentation.view.wallet.create

import com.linkbit.android.presentation.base.View

interface CreateWalletView : View {
    fun setStep(step: Int)
    fun nextButtonEnabled(state: Boolean)
    fun setProgressDialogVisible(visible: Boolean)
}