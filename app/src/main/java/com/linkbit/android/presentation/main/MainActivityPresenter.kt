package com.linkbit.android.presentation.main

import android.os.AsyncTask
import com.linkbit.android.R
import com.linkbit.android.data.model.CoinStatistic
import com.linkbit.android.data.repository.AuthRepository
import com.linkbit.android.data.repository.CoinRepository
import com.linkbit.android.data.repository.WalletRepository
import com.linkbit.android.entity.WalletModel
import com.linkbit.android.presentation.Presenter
import java.util.*




class MainActivityPresenter(
        view: MainActivityView,
        private val authRepository: AuthRepository = AuthRepository(view.getContext()),
        private val coinRepository: CoinRepository = CoinRepository(view.getContext()),
        private val walletRepository: WalletRepository = WalletRepository(view.getContext())
) : Presenter<MainActivityView>(view) {
    var coinMap = HashMap<String, CoinStatistic>()
    var totalExchangeBalace: Double = 0.toDouble()

    fun init() {
        val wallet = ctx.getString(R.string.wallet)
        val transaction = ctx.getString(R.string.transaction)
        val friendList = ctx.getString(R.string.friend_list)
        this.view.addTabSpec(wallet, R.id.tab_wallet, wallet)
        this.view.addTabSpec(transaction, R.id.tab_transaction, transaction)
        this.view.addTabSpec(friendList, R.id.tab_friend, friendList)
        authRepository.getAuthData().subscribe { view.setLinkbitAddress(it.linkbitAddress) }
        walletRepository.getWalletList().subscribe { walletListLoad(it) }.apply { disposables.add(this) }
    }

    fun walletListLoad(walletModelList: List<WalletModel>) {
       object : AsyncTask<Void, Void, Object>() {
            override fun doInBackground(vararg params: Void): Object {
                view.let {
                    walletModelList!!.forEach { wallet ->
                        val coin = coinRepository.getCoinBySymbol(wallet.coinSymbol).toBlocking().value()
                        coinRepository.getCoinPrice(coin.symbol, Locale.getDefault()).subscribe {
                            val price = it
                            val realBalance = wallet.balance * price.amount
                            totalExchangeBalace += realBalance
                            var statistic = coinMap.get(wallet.coinSymbol)
                            if (statistic == null) {
                                statistic = CoinStatistic(coin.symbol!!, price.unit, coin.name!!, wallet.balance!!, realBalance)
                                coinMap.put(coin.symbol!!, statistic)
                            } else {
                                statistic.balance = statistic.balance + wallet.balance!!
                                statistic.price = (statistic.price + realBalance)
                            }
                        }
                    }
                }
                return Object()
            }

           override fun onPostExecute(void: Object) {
               view.setCoinCardItems(coinMap.values.toList())
               view.setTotalExchangeBalance(totalExchangeBalace.toString())
           }
        }.execute()
    }
}

