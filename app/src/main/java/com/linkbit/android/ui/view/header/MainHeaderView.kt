package com.linkbit.android.ui.view.header;

import android.support.v7.widget.RecyclerView
import android.widget.TabHost
import android.widget.TextView
import com.linkbit.android.ui.base.BaseView

interface MainHeaderView : BaseView{
    fun setStatisticRecyclerAdapter(adapter: HeaderCardAdapter)
    fun setLinkbitAddress(address: String)
    fun setTotalExchangeBalance(balance: String)

}
