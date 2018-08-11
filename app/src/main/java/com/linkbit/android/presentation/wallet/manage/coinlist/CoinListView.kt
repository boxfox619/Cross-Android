package com.linkbit.android.presentation.wallet.manage.coinlist

import com.linkbit.android.data.model.coin.CoinModel
import com.linkbit.android.presentation.View


interface CoinListView : View {
    fun setListItems(items: List<CoinModel>): Unit
}