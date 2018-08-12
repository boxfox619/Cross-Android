package com.linkbit.android.presentation.wallet.list

import com.linkbit.android.entity.WalletModel
import com.linkbit.android.presentation.View

interface WalletListView : View {
    fun setWalletItems(items: List<WalletModel>): Unit

}