package com.linkbit.android.presentation.wallet.manage.coinlist

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.linkbit.android.R
import com.linkbit.android.entity.CoinModel
import com.linkbit.android.data.model.wallet.WalletEditModel
import com.linkbit.android.presentation.BaseFragment

class CoinListFragment : BaseFragment<CoinListPresenter>(), CoinListView {

    private lateinit var coinList: ArrayList<CoinModel>
    private lateinit var wallet: WalletEditModel
    private lateinit var isValid: (state: Boolean) -> Unit
    private lateinit var selectionMode: SelectionMode
    private lateinit var adapter: CoinListViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_coin_list, container, false)
        this.presenter = CoinListPresenter(this, wallet, isValid)
        adapter = CoinListViewAdapter(this.coinList, { presenter.itemSeleced(it) }, selectionMode)
        if (view is RecyclerView) {
            view.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
            view.adapter = adapter
        }
        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(coinList: ArrayList<CoinModel>, wallet: WalletEditModel, isValid: (state: Boolean) -> Unit, selectionMode: SelectionMode) = CoinListFragment().apply {
            this.coinList = coinList
            this.wallet = wallet
            this.isValid = isValid
            this.selectionMode = selectionMode
        }
    }
}
