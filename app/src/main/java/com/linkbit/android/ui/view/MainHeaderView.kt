package com.linkbit.android.ui.view;

import com.linkbit.android.adapter.CoinStatisticAdapter
import com.linkbit.android.ui.base.BaseView

interface MainHeaderView : BaseView{
    fun setStatisticRecyclerAdapter(adapter: CoinStatisticAdapter)
    fun setLinkbitAddress(address: String)
    fun setTotalExchangeBalance(balance: String)

}
