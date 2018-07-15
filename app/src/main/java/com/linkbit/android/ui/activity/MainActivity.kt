package com.linkbit.android.ui.activity

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.linkbit.android.R
import com.linkbit.android.presenter.MainHeaderPresenter
import com.linkbit.android.adapter.CoinStatisticAdapter
import com.linkbit.android.adapter.FriendAdapter
import com.linkbit.android.adapter.TransactionAdapter
import com.linkbit.android.adapter.WalletAdapter
import com.linkbit.android.presenter.MainTabPresenter
import com.linkbit.android.ui.view.MainHeaderView
import com.linkbit.android.ui.view.MainTabView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_header.*


class MainActivity : AppCompatActivity(), MainHeaderView, MainTabView {

    lateinit var mainHeaderPresenter: MainHeaderPresenter
    lateinit var mainTabPresenter: MainTabPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainTabPresenter = MainTabPresenter()
        mainTabPresenter.addView(this)
        mainHeaderPresenter = MainHeaderPresenter()
        mainHeaderPresenter.addView(this)
    }

    override fun setWalletListAdapter(adapter: WalletAdapter) {
        recyclerview_wallet.adapter = adapter
    }

    override fun setFriendListAdapter(adapter: FriendAdapter) {
        recyclerview_friend.adapter= adapter
    }

    override fun setTransactionListAdapter(adapter: TransactionAdapter) {
        recyclerview_transaction.adapter= adapter
    }

    override fun addTabSpec(tabName: String, contentId: Int, indicator: String) {
        val spec = tab_host.newTabSpec(tabName)
        spec.setContent(contentId)
        spec.setIndicator(indicator)
        tab_host.addTab(spec)
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

    override fun setStatisticRecyclerAdapter(adapter: CoinStatisticAdapter) {
        recyclerview_coin_statistics.adapter = adapter
    }
}
