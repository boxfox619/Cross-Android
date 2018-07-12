package com.linkbit.android.presenter

import com.linkbit.android.ui.view.header.HeaderView


class HeaderPresenter : BasePresenter<HeaderView>{

    lateinit var headerView: HeaderView

    fun load() {
        headerView.let {

            it.getRecyclerView().a
        }
    }

    override fun addView(view: HeaderView) {
        this.headerView=view
    }

    override fun removeView() {
        this.headerView==null
    }
}

