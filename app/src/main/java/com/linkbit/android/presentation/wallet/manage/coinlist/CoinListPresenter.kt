package com.linkbit.android.presentation.wallet.manage.coinlist

import com.linkbit.android.entity.CoinModel
import com.linkbit.android.data.model.wallet.WalletEditModel
import com.linkbit.android.data.repository.CoinRepository
import com.linkbit.android.presentation.Presenter


class CoinListPresenter (view: CoinListView,
                         wallet: WalletEditModel,
                         isValid : (state:Boolean)->Unit,
                         selectionMode: SelectionMode,
                         private val coinRepository: CoinRepository = CoinRepository(view.getContext())) : Presenter<CoinListView>(view) {
    val wallet: WalletEditModel = wallet
    val isValid: (state:Boolean) -> Unit = isValid
    var selectionMode: SelectionMode = selectionMode
    private var items = ArrayList<CoinModel>()

    init {
        isValid(false)
    }

    fun load(){
        coinRepository.getSupportCoins().subscribe { supportCoinList ->
            if(supportCoinList!=null){
                view.setListItems(supportCoinList)
            }
        }
    }

    fun itemSeleced(item: CoinModel){
        if(selectionMode == SelectionMode.SINGLE){
            wallet.coin = item
            isValid(true)
        }else{
            if(wallet.subCoinList.contains(item)){
                wallet.subCoinList.remove(item)
            }else{
                wallet.subCoinList.add(item)
            }
            isValid(wallet.subCoinList.size > 0)
        }
    }

    override fun destory() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

