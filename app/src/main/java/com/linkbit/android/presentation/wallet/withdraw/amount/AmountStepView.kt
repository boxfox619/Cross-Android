package com.linkbit.android.presentation.wallet.list

import com.linkbit.android.presentation.View

interface AmountStepView : View {

    fun addTabSpec(tabName: String, label: String, iconId: Int)
}