package com.linkbit.android.ui.activity

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import com.linkbit.android.R
import com.linkbit.android.presenter.MainActivityPresenter
import com.linkbit.android.ui.view.header.MainView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_header.*

class MainActivity : AppCompatActivity(), MainView {

    lateinit var mainActivityPresenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActivityPresenter = MainActivityPresenter()
        mainActivityPresenter.addView(this)
    }

    override fun getContext(): Context {
        return this
    }

    override fun getAddressTextView(): TextView {
        return tv_linkbit_address
    }

    override fun getTotalBalanceTextView(): TextView {
        return tv_total_exchange_balance
    }
    override fun getWalletRecylcerView(): RecyclerView {
        return recyclerview_wallet
    }

    override fun getStatisticRecyclerView(): RecyclerView {
        return recyclerview_coin_statistics
    }
}
