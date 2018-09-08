package com.linkbit.android.presentation.coin.list

import com.linkbit.android.entity.CoinModel
import com.linkbit.android.data.model.wallet.WalletEditModel
import com.linkbit.android.presentation.Presenter


class CoinListPresenter (view: CoinListView,
                         wallet: WalletEditModel,
                         isValid : (state:Boolean)->Unit) : Presenter<CoinListView>(view) {
    val wallet: WalletEditModel = wallet
    val isValid: (state:Boolean) -> Unit = isValid

    fun itemSeleced(item: CoinModel?){
        if (item != null) {
            wallet.coin = item
        }
        isValid(item!=null)
    }
}

