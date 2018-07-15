package com.linkbit.android.ui.view;

import com.linkbit.android.adapter.FriendAdapter
import com.linkbit.android.adapter.TransactionAdapter
import com.linkbit.android.adapter.WalletAdapter
import com.linkbit.android.ui.base.BaseView

interface MainTabView : BaseView{
    fun addTabSpec(tabName: String, contentId: Int, indicator: String)
    fun setWalletListAdapter(adapter: WalletAdapter)
    fun setFriendListAdapter(adapter: FriendAdapter)
    fun setTransactionListAdapter(adapter: TransactionAdapter)

}
