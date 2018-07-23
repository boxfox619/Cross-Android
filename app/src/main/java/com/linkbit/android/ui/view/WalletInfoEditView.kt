package com.linkbit.android.ui.view

import com.linkbit.android.model.WalletEditModel
import com.linkbit.android.ui.base.BaseView


interface WalletInfoEditView : BaseView{
    fun initView(wallet: WalletEditModel)
}