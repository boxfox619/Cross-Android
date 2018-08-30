package com.linkbit.android.presentation.main

import com.linkbit.android.R
import com.linkbit.android.data.model.CoinStatistic
import com.linkbit.android.data.repository.CoinRepository
import com.linkbit.android.data.repository.WalletRepository
import com.linkbit.android.entity.WalletModel
import com.linkbit.android.presentation.Presenter
import java.util.*


class MainActivityPresenter(
        view: MainActivityView,
        private val coinRepository: CoinRepository = CoinRepository(view.getContext()),
        private val walletRepository: WalletRepository = WalletRepository(view.getContext())
) : Presenter<MainActivityView>(view) {

    fun init() {
        val wallet = ctx.getString(R.string.wallet)
        val transaction = ctx.getString(R.string.transaction)
        val friendList = ctx.getString(R.string.friend_list)
        /*this.view.addTabSpec(wallet, R.id.tab_wallet, wallet)
        this.view.addTabSpec(transaction, R.id.tab_transaction, transaction)
        this.view.addTabSpec(friendList, R.id.tab_friend, friendList)*/
        walletRepository.getWalletList().subscribe { walletListLoad(it) }.apply { disposables.add(this) }
    }

    fun walletListLoad(walletModelList: List<WalletModel>) {
        this.view.let {
            var coinMap = HashMap<String, CoinStatistic>()
            var totalExchangeBalace: Double = 0.toDouble()
            walletModelList!!.forEach {
                val coin = coinRepository.getCoinBySymbol(it.coinSymbol).toBlocking().value()
                val price = coinRepository.getCoinPrice(coin.symbol, Locale.getDefault()).toBlocking().value()
                val realBalance = it.balance * price.amount
                totalExchangeBalace += realBalance
                var statistic = coinMap.get(it.coinSymbol)
                if (statistic == null) {
                    statistic = CoinStatistic(coin.symbol!!, price.unit, coin.name!!, it.balance!!, realBalance)
                    coinMap.put(coin.symbol!!, statistic)
                } else {
                    statistic.balance = statistic.balance + it.balance!!
                    statistic.price = (statistic.price + realBalance)
                }
            }
            view.setCoinCardItems(coinMap.values.toList())
            view.setTotalExchangeBalance(totalExchangeBalace.toString())
        }
    }
}

