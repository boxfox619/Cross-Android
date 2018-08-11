package com.linkbit.android.presentation.wallet.manage.coinlist

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.linkbit.android.R
import com.linkbit.android.helper.SelectionMode
import com.linkbit.android.data.model.wallet.WalletEditModel
import com.linkbit.android.presentation.BaseFragment

class CoinListFragment : BaseFragment<CoinListPresenter>(), CoinListView {

    private lateinit var wallet: WalletEditModel
    private lateinit var isValid: (state: Boolean) -> Unit
    private lateinit var selectionMode: SelectionMode
    override val presenter = CoinListPresenter(this, wallet, isValid, selectionMode)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_coin_list, container, false)
        if (view is RecyclerView) {
            view.adapter = presenter.adapter
        }
        presenter.load()
        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(wallet: WalletEditModel, isValid: (state: Boolean) -> Unit, selectionMode: SelectionMode) = CoinListFragment().apply {
            this.wallet = wallet
            this.isValid = isValid
            this.selectionMode = selectionMode
        }
    }
}
