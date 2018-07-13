package com.linkbit.android.ui.view.header;

import android.support.v7.widget.RecyclerView
import android.widget.TextView
import com.linkbit.android.ui.base.BaseView

interface MainView : BaseView{
    fun getStatisticRecyclerView() : RecyclerView
    fun getAddressTextView(): TextView
    fun getTotalBalanceTextView(): TextView

}
