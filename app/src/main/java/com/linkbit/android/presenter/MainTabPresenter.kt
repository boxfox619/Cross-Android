package com.linkbit.android.presenter

import com.linkbit.android.ui.view.MainTabView
import com.linkbit.android.R
import com.linkbit.android.adapter.WalletAdapter
import com.linkbit.android.model.Wallet
import com.linkbit.android.service.WalletService
import rx.Subscription


class MainTabPresenter : BasePresenter<MainTabView> {

    lateinit var mainTabView: MainTabView
    lateinit var walletListSubscriber: Subscription
    lateinit var walletListAdapter: WalletAdapter

    fun walletListLoad(walletList: List<Wallet>){
        this.mainTabView.let {
            this.walletListAdapter.clear()
            walletList.forEach { this.walletListAdapter.addItem(it) }
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
        this.mainTabView.setWalletListAdapter(walletListAdapter)
        this.walletListSubscriber = WalletService.instance.walletList.subscribe({ walletListLoad(it) })
    }

    override fun removeView() {
        this.mainTabView == null
        this.walletListSubscriber.unsubscribe()
    }
}

