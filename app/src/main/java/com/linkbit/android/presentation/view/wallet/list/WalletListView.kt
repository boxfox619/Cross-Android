package com.linkbit.android.presentation.view.wallet.list

import com.linkbit.android.entity.WalletModel
import com.linkbit.android.presentation.base.View

interface WalletListView : View {
    fun setWalletItems(items: List<WalletModel>): Unit

}