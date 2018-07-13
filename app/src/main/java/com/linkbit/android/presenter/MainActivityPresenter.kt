package com.linkbit.android.presenter

import com.linkbit.android.model.CoinStatistic
import com.linkbit.android.model.Wallet
import com.linkbit.android.network.Connector
import com.linkbit.android.network.Response
import com.linkbit.android.ui.view.header.HeaderCardAdapter
import com.linkbit.android.ui.view.header.MainView
import com.linkbit.android.ui.view.header.WalletAdapter


class MainActivityPresenter : BasePresenter<MainView>{

    lateinit var mainView: MainView
    lateinit var headerAdapter: HeaderCardAdapter
    lateinit var walletAdapter: WalletAdapter

    fun load() {
        mainView.let {
            headerAdapter = HeaderCardAdapter(it.getContext())
            it.getStatisticRecyclerView().adapter = headerAdapter
            Connector(it.getContext()).walletAPI.getWalletList().enqueue((object: Response<List<Wallet>>(it.getContext()!!){
                override fun setResponseData(code: Int, walletList: List<Wallet>?) {
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
                    coinMap.values.forEach { headerAdapter.addItem(it) }
                    it.getTotalBalanceTextView().text = totalExchangeBalace.toString()
                }
            }))

        }
    }

    override fun addView(view: MainView) {
        this.mainView=view
    }

    override fun removeView() {
        this.mainView==null
    }
}

