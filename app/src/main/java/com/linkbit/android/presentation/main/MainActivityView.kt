package com.linkbit.android.presentation.main;

import com.linkbit.android.data.model.CoinStatistic
import com.linkbit.android.entity.UserModel
import com.linkbit.android.presentation.View

interface MainActivityView : View {
    fun setLinkbitAddress(address: String)
    fun setTotalExchangeBalance(balance: String)
    fun addTabSpec(tabName: String, contentId: Int, indicator: String)
    fun setCoinCardItems(items: List<CoinStatistic>)
    fun setAuthInfo(authData: UserModel)

}
