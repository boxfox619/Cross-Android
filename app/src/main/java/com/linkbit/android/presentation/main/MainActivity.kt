package com.linkbit.android.presentation.main

import android.content.Context
import android.os.Bundle
import com.linkbit.android.R
import com.linkbit.android.adapter.CoinStatisticAdapter
import com.linkbit.android.presentation.friend.list.FriendListAdapter
import com.linkbit.android.adapter.TransactionAdapter
import com.linkbit.android.adapter.WalletAdapter
import com.linkbit.android.data.model.CoinStatistic
import com.linkbit.android.presentation.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_header.*

class MainActivity : BaseActivity<MainActivityPresenter>(), MainActivityView {
    override val presenter: MainActivityPresenter = MainActivityPresenter(this)
    lateinit var coinStatisticAdapter: CoinStatisticAdapter
    lateinit var transactionListAdapter: TransactionAdapter
    lateinit var walletListAdapter: WalletAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.coinStatisticAdapter = CoinStatisticAdapter(this)
        this.transactionListAdapter = TransactionAdapter(this)
        this.walletListAdapter = WalletAdapter(this)
        recyclerview_coin_statistics.adapter = coinStatisticAdapter
        recyclerview_transaction.adapter= transactionListAdapter
        recyclerview_wallet.adapter = walletListAdapter

        presenter.init()
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

    override fun setCoinCardItems(items: List<CoinStatistic>) {
        coinStatisticAdapter.clear()
        coinStatisticAdapter.addItems(items)
    }

}
