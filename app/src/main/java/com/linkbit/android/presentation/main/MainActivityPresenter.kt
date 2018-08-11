package com.linkbit.android.presentation.main

import com.linkbit.android.R
import com.linkbit.android.data.model.CoinStatistic
import com.linkbit.android.data.model.wallet.Wallet
import com.linkbit.android.service.WalletService
import com.linkbit.android.adapter.CoinStatisticAdapter
import com.linkbit.android.adapter.FriendAdapter
import com.linkbit.android.adapter.TransactionAdapter
import com.linkbit.android.adapter.WalletAdapter
import com.linkbit.android.data.model.TransactionStatus
import com.linkbit.android.data.model.User
import com.linkbit.android.presentation.Presenter
import com.linkbit.android.service.FriendService
import rx.Subscription


class MainActivityPresenter(view: MainActivityView) : Presenter<MainActivityView>(view) {
    lateinit var walletListSubscriber: Subscription
    lateinit var headerAdapter: CoinStatisticAdapter
    lateinit var walletListAdapter: WalletAdapter

    lateinit var transactionListSubscription: Subscription
    lateinit var transactionListAdapter: TransactionAdapter

    lateinit var friendListSubscription: Subscription
    lateinit var friendListAdapter: FriendAdapter

    fun load(walletList: List<Wallet>) {
        view.let {
            val headerView = it
            headerAdapter!!.clear()
            var coinMap = HashMap<String, CoinStatistic>()
            var totalExchangeBalace: Double = 0.toDouble()
            walletList!!.forEach {
                totalExchangeBalace += it.krBalance
                var statistic = coinMap.get(it.coin)
                if (statistic == null) {
                    statistic = CoinStatistic(it.coin!!, "KRW", it.coinName!!, it.balance!!, it.krBalance.toLong())
                    coinMap.put(it.coin!!, statistic)
                } else {
                    statistic.balance = statistic.balance + it.balance!!
                    statistic.price = (statistic.price + it.krBalance).toLong()
                }
            }
            coinMap.values.forEach { headerAdapter!!.addItem(it) }
            headerView.setTotalExchangeBalance(totalExchangeBalace.toString())
        }
    }

    fun init() {
        this.headerAdapter = CoinStatisticAdapter(ctx)
        this.view.setStatisticRecyclerAdapter(this.headerAdapter)
        val wallet = ctx.getString(R.string.wallet)
        val transaction = ctx.getString(R.string.transaction)
        val friendList = ctx.getString(R.string.friend_list)
        this.view.addTabSpec(wallet, R.id.tab_wallet, wallet)
        this.view.addTabSpec(transaction, R.id.tab_transaction, transaction)
        this.view.addTabSpec(friendList, R.id.tab_friend, friendList)
        this.walletListAdapter = WalletAdapter(ctx)
        this.friendListAdapter = FriendAdapter(ctx)
        this.transactionListAdapter = TransactionAdapter(ctx)
        this.view.setWalletListAdapter(walletListAdapter)
        this.view.setFriendListAdapter(friendListAdapter)
        this.view.setTransactionListAdapter(transactionListAdapter)
        this.walletListSubscriber = WalletService.instance.walletList.subscribe({ walletListLoad(it) })
        this.friendListSubscription = FriendService.instance.friendList.subscribe({ friendListLoad(it) })
        this.transactionListSubscription = WalletService.instance.transactionList.subscribe({ transactionListLoad(it) })
    }

    override fun destory() {
        walletListSubscriber.unsubscribe()
        this.friendListSubscription.unsubscribe()
        this.walletListSubscriber.unsubscribe()
        this.transactionListSubscription.unsubscribe()
    }

    fun walletListLoad(walletList: List<Wallet>) {
        this.view.let {
            this.walletListAdapter.clear()
            walletList.forEach { this.walletListAdapter.addItem(it) }
        }
    }

    fun friendListLoad(friendList: List<User>) {
        this.view.let {
            this.friendListAdapter.clear()
            friendList.forEach { this.friendListAdapter.addItem(it) }
        }
    }

    fun transactionListLoad(transactionList: List<TransactionStatus>) {
        this.view.let {
            this.transactionListAdapter.clear()
            transactionList.forEach { this.transactionListAdapter.addItem(it) }
        }
    }
}

