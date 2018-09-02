package com.linkbit.android.presentation.wallet.manage.coinlist

import com.linkbit.android.entity.CoinModel
import com.linkbit.android.data.model.wallet.WalletEditModel
import com.linkbit.android.data.repository.CoinRepository
import com.linkbit.android.presentation.Presenter


class CoinListPresenter (view: CoinListView,
                         wallet: WalletEditModel,
                         isValid : (state:Boolean)->Unit,
                         selectionMode: SelectionMode) : Presenter<CoinListView>(view) {
    val wallet: WalletEditModel = wallet
    val isValid: (state:Boolean) -> Unit = isValid
    var selectionMode: SelectionMode = selectionMode

    init {
        isValid(false)
    }

    fun itemSeleced(item: CoinModel){
        wallet.coin = item
        isValid(true)
    }
}

