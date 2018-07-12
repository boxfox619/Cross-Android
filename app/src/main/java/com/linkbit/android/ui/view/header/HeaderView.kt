package com.linkbit.android.ui.view.header;

import android.support.v7.widget.RecyclerView
import com.linkbit.android.ui.base.BaseView

interface HeaderView : BaseView{
    fun getRecyclerView() : RecyclerView

}
