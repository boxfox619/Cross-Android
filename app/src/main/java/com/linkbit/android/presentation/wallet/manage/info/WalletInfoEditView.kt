package com.linkbit.android.presentation.wallet.manage.info

import com.linkbit.android.model.WalletEditModel
import com.linkbit.android.presentation.View


interface WalletInfoEditView : View {
    fun initView(wallet: WalletEditModel)
}