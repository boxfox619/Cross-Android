package com.linkbit.android.presentation.main

import android.content.Context
import android.os.Bundle
import com.linkbit.android.R
import com.linkbit.android.data.model.CoinStatistic
import com.linkbit.android.presentation.BaseActivity
import com.linkbit.android.presentation.friend.list.FriendListFragment
import com.linkbit.android.presentation.trasnaction.list.TransactionListFragment
import com.linkbit.android.presentation.wallet.list.WalletListFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_header.*

class MainActivity : BaseActivity<MainActivityPresenter>(), MainActivityView {
    override val presenter: MainActivityPresenter = MainActivityPresenter(this)
    lateinit var coinStatisticAdapter: CoinStatisticAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.coinStatisticAdapter = CoinStatisticAdapter(this)
        /*recyclerview_coin_statistics.adapter = coinStatisticAdapter*/
        val manager = fragmentManager
        manager.beginTransaction().replace(R.id.tab_wallet, WalletListFragment.newInstance()).commit()
        manager.beginTransaction().replace(R.id.tab_friend, FriendListFragment.newInstance()).commit()
        manager.beginTransaction().replace(R.id.tab_transaction, TransactionListFragment.newInstance()).commit()
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
        /*tv_linkbit_address.text = address*/
    }

    override fun setTotalExchangeBalance(balance: String) {
        /*tv_total_exchange_balance.text = balance*/
    }

    override fun setCoinCardItems(items: List<CoinStatistic>) {
        coinStatisticAdapter.clear()
        coinStatisticAdapter.addItems(items)
    }

}
