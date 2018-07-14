package com.linkbit.android.ui.activity

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.linkbit.android.R
import com.linkbit.android.presenter.MainHeaderPresenter
import com.linkbit.android.ui.view.header.HeaderCardAdapter
import com.linkbit.android.ui.view.header.MainHeaderView
import kotlinx.android.synthetic.main.layout_header.*

class MainActivity : AppCompatActivity(), MainHeaderView {

    lateinit var mainHeaderPresenter: MainHeaderPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainHeaderPresenter = MainHeaderPresenter()
        mainHeaderPresenter.addView(this)
    }

    override fun getContext(): Context {
        return this
    }

    override fun setLinkbitAddress(address: String) {
        tv_linkbit_address.text = address
    }

    override fun setTotalExchangeBalance(balance: String) {
        tv_total_exchange_balance.text = balance
    }

    override fun setStatisticRecyclerAdapter(adapter: HeaderCardAdapter) {
        recyclerview_coin_statistics.adapter = adapter
    }
}
