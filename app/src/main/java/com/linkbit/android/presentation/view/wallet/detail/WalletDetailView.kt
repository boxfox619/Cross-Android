package com.linkbit.android.presentation.view.wallet.detail

import com.linkbit.android.entity.WalletModel
import com.linkbit.android.presentation.base.View

interface WalletDetailView : View {
    fun initWalletInfo(walletModel: WalletModel)
}