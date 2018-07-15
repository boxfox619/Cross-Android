package com.linkbit.android.presenter

import com.linkbit.android.ui.view.MainTabView
import com.linkbit.android.R
import com.linkbit.android.adapter.FriendAdapter
import com.linkbit.android.adapter.TransactionAdapter
import com.linkbit.android.adapter.WalletAdapter
import com.linkbit.android.model.TransactionStatus
import com.linkbit.android.model.User
import com.linkbit.android.model.Wallet
import com.linkbit.android.service.FriendService
import com.linkbit.android.service.WalletService
import rx.Subscription


class MainTabPresenter : BasePresenter<MainTabView> {

    lateinit var mainTabView: MainTabView
    lateinit var walletListSubscriber: Subscription
    lateinit var walletListAdapter: WalletAdapter

    lateinit var transactionListSubscription: Subscription
    lateinit var transactionListAdapter: TransactionAdapter

    lateinit var friendListSubscription: Subscription
    lateinit var friendListAdapter: FriendAdapter

    fun walletListLoad(walletList: List<Wallet>) {
        this.mainTabView.let {
            this.walletListAdapter.clear()
            walletList.forEach { this.walletListAdapter.addItem(it) }
        }
    }

    fun friendListLoad(friendList: List<User>) {
        this.mainTabView.let {
            this.friendListAdapter.clear()
            friendList.forEach { this.friendListAdapter.addItem(it) }
        }
    }

    fun transactionListLoad(transactionList: List<TransactionStatus>) {
        this.mainTabView.let {
            this.transactionListAdapter.clear()
            transactionList.forEach { this.transactionListAdapter.addItem(it) }
        }
    }


    override fun addView(tabView: MainTabView) {
        this.mainTabView = tabView
        val ctx = this.mainTabView.getContext()
        val wallet = ctx.getString(R.string.wallet)
        val transaction = ctx.getString(R.string.transaction)
        val friendList = ctx.getString(R.string.friend_list)
        this.mainTabView.addTabSpec(wallet, R.id.tab_wallet, wallet)
        this.mainTabView.addTabSpec(transaction, R.id.tab_transaction, transaction)
        this.mainTabView.addTabSpec(friendList, R.id.tab_friend, friendList)
        this.walletListAdapter = WalletAdapter(ctx)
        this.friendListAdapter = FriendAdapter(ctx)
        this.transactionListAdapter = TransactionAdapter(ctx)
        this.mainTabView.setWalletListAdapter(walletListAdapter)
        this.mainTabView.setFriendListAdapter(friendListAdapter)
        this.mainTabView.setTransactionListAdapter(transactionListAdapter)
        this.walletListSubscriber = WalletService.instance.walletList.subscribe({ walletListLoad(it) })
        this.friendListSubscription = FriendService.instance.friendList.subscribe({ friendListLoad(it) })
        this.transactionListSubscription = WalletService.instance.transactionList.subscribe({ transactionListLoad(it) })
    }

    override fun removeView() {
        this.mainTabView == null
        this.friendListSubscription.unsubscribe()
        this.walletListSubscriber.unsubscribe()
        this.transactionListSubscription.unsubscribe()
    }
}

