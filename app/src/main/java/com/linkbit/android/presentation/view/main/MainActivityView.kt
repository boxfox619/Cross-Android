package com.linkbit.android.presentation.view.main;

import com.linkbit.android.data.model.CoinStatistic
import com.linkbit.android.presentation.base.View

interface MainActivityView : View {
    fun setLinkbitAddress(address: String)
    fun setTotalExchangeBalance(balance: String)
    fun addTabSpec(tabName: String, contentId: Int, indicator: String)
    fun setCoinCardItems(items: List<CoinStatistic>)
}
