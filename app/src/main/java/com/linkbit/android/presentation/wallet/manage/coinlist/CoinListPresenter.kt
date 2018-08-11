package com.linkbit.android.presentation.wallet.manage.coinlist

import com.linkbit.android.adapter.CoinListViewAdapter
import com.linkbit.android.helper.SelectionMode
import com.linkbit.android.model.coin.CoinModel
import com.linkbit.android.model.WalletEditModel
import com.linkbit.android.data.network.Connector
import com.linkbit.android.data.network.Response
import com.linkbit.android.presentation.Presenter


class CoinListPresenter (view: CoinListView, wallet: WalletEditModel, isValid : (state:Boolean)->Unit, selectionMode: SelectionMode) : Presenter<CoinListView>(view) {

    val wallet: WalletEditModel = wallet
    val adapter: CoinListViewAdapter
    val isValid: (state:Boolean) -> Unit = isValid
    var selectionMode: SelectionMode
    private var items = ArrayList<CoinModel>()

    init {
        adapter = CoinListViewAdapter(items, {itemSeleced(it)}, selectionMode)
        this.selectionMode = selectionMode
        isValid(false)
    }

    fun load(){
        Connector(view.getContext()).walletAPI.getSupportedCoins().enqueue((object : Response<List<CoinModel>>(view.getContext()) {
            override fun setResponseData(code: Int, supportCoinList: List<CoinModel>?) {
                if (isSuccess(code)) {
                    items.addAll(supportCoinList!!)
                    adapter.notifyDataSetChanged()
                }
            }
        }))
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

