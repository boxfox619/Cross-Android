package com.linkbit.android.ui.view;

import com.linkbit.android.adapter.HeaderCardAdapter
import com.linkbit.android.ui.base.BaseView

interface MainHeaderView : BaseView{
    fun setStatisticRecyclerAdapter(adapter: HeaderCardAdapter)
    fun setLinkbitAddress(address: String)
    fun setTotalExchangeBalance(balance: String)

}
