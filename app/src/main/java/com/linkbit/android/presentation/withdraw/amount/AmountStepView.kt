package com.linkbit.android.presentation.wallet.list

import com.linkbit.android.entity.WalletModel
import com.linkbit.android.presentation.View

interface AmountStepView : View {

    fun setSoruceWalletInfo(wallet: WalletModel)

    fun setTargetWalletInfo(wallet: WalletModel)

    fun addTabSpec(tabName: String, label: String, iconId: Int)
}