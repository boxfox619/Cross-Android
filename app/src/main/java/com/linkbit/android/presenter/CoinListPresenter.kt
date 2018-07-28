package com.linkbit.android.presenter

import com.linkbit.android.adapter.CoinListViewAdapter
import com.linkbit.android.helper.SelectionMode
import com.linkbit.android.model.Coin
import com.linkbit.android.model.WalletEditModel
import com.linkbit.android.network.Connector
import com.linkbit.android.network.Response
import com.linkbit.android.ui.view.CoinListView


class CoinListPresenter (wallet: WalletEditModel, isValid : (state:Boolean)->Unit, selectionMode: SelectionMode) : BasePresenter<CoinListView> {

    lateinit var view: CoinListView
    val wallet: WalletEditModel = wallet
    val adapter: CoinListViewAdapter
    val isValid: (state:Boolean) -> Unit = isValid
    private var items = ArrayList<Coin>()

    init {
        adapter = CoinListViewAdapter(items, {itemSeleced(it)}, selectionMode)
    }

    fun load(){
        Connector(view.getContext()).walletAPI.getSupportedCoins().enqueue((object : Response<List<Coin>>(view.getContext()) {
            override fun setResponseData(code: Int, supportCoinList: List<Coin>?) {
                if (isSuccess(code)) {
                    items.addAll(supportCoinList!!)
                    adapter.notifyDataSetChanged()
                }
            }
        }))
    }

    fun itemSeleced(item: Coin){
    }

    override fun addView(view: CoinListView) {
        this.view = view
    }
    override fun removeView() {
        this.view == null
    }
}

