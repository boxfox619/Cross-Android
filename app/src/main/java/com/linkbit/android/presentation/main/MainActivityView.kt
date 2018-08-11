package com.linkbit.android.presentation.main;

import com.linkbit.android.adapter.CoinStatisticAdapter
import com.linkbit.android.adapter.FriendAdapter
import com.linkbit.android.adapter.TransactionAdapter
import com.linkbit.android.adapter.WalletAdapter
import com.linkbit.android.presentation.View

interface MainActivityView : View {
    fun setStatisticRecyclerAdapter(adapter: CoinStatisticAdapter)
    fun setLinkbitAddress(address: String)
    fun setTotalExchangeBalance(balance: String)
    fun addTabSpec(tabName: String, contentId: Int, indicator: String)
    fun setWalletListAdapter(adapter: WalletAdapter)
    fun setFriendListAdapter(adapter: FriendAdapter)
    fun setTransactionListAdapter(adapter: TransactionAdapter)

}
