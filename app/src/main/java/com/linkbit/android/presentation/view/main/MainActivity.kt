package com.linkbit.android.presentation.view.main

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.linkbit.android.R
import com.linkbit.android.presentation.adapter.coin.CoinStatisticAdapter
import com.linkbit.android.data.model.CoinStatistic
import com.linkbit.android.presentation.base.BaseActivity
import com.linkbit.android.presentation.view.friend.list.FriendListFragment
import com.linkbit.android.presentation.trasnaction.list.TransactionListFragment
import com.linkbit.android.presentation.view.wallet.list.WalletListFragment
import com.linkbit.android.presentation.view.wallet.create.CreateWalletActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_header.*
import com.linkbit.android.entity.WalletModel
import com.linkbit.android.presentation.view.wallet.detail.WalletDetailActivity


class MainActivity : BaseActivity<MainActivityPresenter>(), MainActivityView {

    override val presenter: MainActivityPresenter = MainActivityPresenter(this)
    lateinit var coinStatisticAdapter: CoinStatisticAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // set light theme to status bar
        var view = window.decorView
        var flags = view.systemUiVisibility
        flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        view.systemUiVisibility = flags
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.WHITE
        }

        this.coinStatisticAdapter = CoinStatisticAdapter(this)
        recyclerview_main_coin_statistics.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        recyclerview_main_coin_statistics.adapter = coinStatisticAdapter
        btn_main_create_wallet.setOnClickListener { startActivity(Intent(this, CreateWalletActivity::class.java)) }
        tab_host.setup()

        val manager = fragmentManager
        manager.beginTransaction().replace(R.id.tab_wallet_content, WalletListFragment.newInstance(this) { startWalletDetail(it) }).commit()
        manager.beginTransaction().replace(R.id.tab_friend_content, FriendListFragment.newInstance()).commit()
        manager.beginTransaction().replace(R.id.tab_transaction_content, TransactionListFragment.newInstance()).commit()
        setSupportActionBar(toolbar_main)
        supportActionBar!!.let {
            it.title = ""
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp)
        }
        presenter.init()
    }

    private fun startWalletDetail(wallet: WalletModel) {
        val intent = Intent(this, WalletDetailActivity::class.java)
        intent.putExtra("address", wallet.linkbitAddress)
        startActivity(intent)
    }

    override fun addTabSpec(tabName: String, contentId: Int, indicator: String) {
        val spec = tab_host.newTabSpec(tabName)
        spec.setContent(contentId)
        spec.setIndicator(indicator)
        tab_host.addTab(spec)
        tab_host.tabWidget.getChildAt(tab_host.tabWidget.childCount - 1).layoutParams.height = (tab_host.tabWidget.getChildAt(tab_host.tabWidget.childCount - 1).layoutParams.height / 1.5).toInt()
    }

    override fun getContext(): Context {
        return this
    }

    override fun setLinkbitAddress(address: String) {
        tv_header_linkbit_address.text = address
    }

    override fun setTotalExchangeBalance(balance: String) {
        tv_header_total_exchange_balance.text = balance
    }

    override fun setCoinCardItems(items: List<CoinStatistic>) {
        coinStatisticAdapter.clear()
        coinStatisticAdapter.addItems(items)
        coinStatisticAdapter.notifyDataSetChanged()
    }

}
