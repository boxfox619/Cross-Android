package com.linkbit.android.presenter

import com.linkbit.android.model.CoinStatistic
import com.linkbit.android.model.Wallet
import com.linkbit.android.service.WalletService
import com.linkbit.android.adapter.CoinStatisticAdapter
import com.linkbit.android.ui.view.MainHeaderView
import rx.Subscription


class MainHeaderPresenter : BasePresenter<MainHeaderView> {

    lateinit var mainHeaderView: MainHeaderView
    lateinit var walletListSubscriber: Subscription
    lateinit var headerAdapter: CoinStatisticAdapter

    fun load(walletList: List<Wallet>) {
        mainHeaderView.let {
            val headerView = it
            headerAdapter!!.clear()
            var coinMap = HashMap<String, CoinStatistic>()
            var totalExchangeBalace: Double = 0.toDouble()
            walletList!!.forEach {
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
            coinMap.values.forEach { headerAdapter!!.addItem(it) }
            headerView.setTotalExchangeBalance(totalExchangeBalace.toString())
        }
    }

    override fun addView(headerView: MainHeaderView) {
        this.mainHeaderView = headerView
        this.headerAdapter = CoinStatisticAdapter(this.mainHeaderView.getContext())
        this.mainHeaderView.setStatisticRecyclerAdapter(this.headerAdapter)
        this.walletListSubscriber = WalletService.instance.walletList.subscribe({ load(it) })
    }

    override fun removeView() {
        walletListSubscriber.unsubscribe()
        this.mainHeaderView == null
    }
}

