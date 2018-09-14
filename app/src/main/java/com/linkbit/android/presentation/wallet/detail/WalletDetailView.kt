package com.linkbit.android.presentation.wallet.detail

import com.linkbit.android.entity.WalletModel
import com.linkbit.android.presentation.View

interface WalletDetailView : View {
    fun initWalletInfo(walletModel: WalletModel)
}