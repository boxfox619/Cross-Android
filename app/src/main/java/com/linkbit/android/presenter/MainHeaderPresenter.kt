package com.linkbit.android.presenter

import com.linkbit.android.model.CoinStatistic
import com.linkbit.android.service.WalletService
import com.linkbit.android.ui.view.header.HeaderCardAdapter
import com.linkbit.android.ui.view.header.MainHeaderView
import com.linkbit.android.ui.view.header.WalletAdapter


class MainHeaderPresenter : BasePresenter<MainHeaderView> {

    lateinit var mainHeaderView: MainHeaderView
    lateinit var headerAdapter: HeaderCardAdapter
    lateinit var walletAdapter: WalletAdapter

    fun load() {
        mainHeaderView.let {
            val headerView = it
            headerAdapter = HeaderCardAdapter(headerView.getContext())
            headerView.setStatisticRecyclerAdapter(headerAdapter)
            WalletService.instance.getWalletList(headerView.getContext(), {
                var coinMap = HashMap<String, CoinStatistic>()
                var totalExchangeBalace: Double = 0.toDouble()
                it!!.forEach {
                    walletAdapter.addItem(it)
                    totalExchangeBalace += it.krBalance
                    var statistic = coinMap.get(it.coinSymbol)
                    if (statistic == null) {
                        statistic = CoinStatistic(it.coinSymbol!!, "KRW", it.coinName!!, it.balance!!, it.krBalance.toLong())
                        coinMap.put(it.coinSymbol!!, statistic)
                    } else {
                        statistic.balance = statistic.balance + it.balance!!
                        statistic.price = (statistic.price + it.krBalance).toLong()
                    }
                }
                coinMap.values.forEach { headerAdapter.addItem(it) }
                headerView.setTotalExchangeBalance(totalExchangeBalace.toString())
            })

        }
    }

    override fun addView(headerView: MainHeaderView) {
        this.mainHeaderView = headerView
    }

    override fun removeView() {
        this.mainHeaderView == null
    }
}

