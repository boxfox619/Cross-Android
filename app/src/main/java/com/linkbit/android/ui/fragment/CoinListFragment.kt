package com.linkbit.android.ui.fragment

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.linkbit.android.R
import com.linkbit.android.helper.SelectionMode
import com.linkbit.android.model.WalletEditModel
import com.linkbit.android.presenter.CoinListPresenter
import com.linkbit.android.ui.view.CoinListView

class CoinListFragment : Fragment(), CoinListView {

    private lateinit var presenter: CoinListPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_coin_list, container, false)
        presenter.addView(this)
        if (view is RecyclerView) {
            view.adapter = presenter.adapter
        }
        presenter.load()
        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(wallet: WalletEditModel, isValid: (state: Boolean)-> Unit, selectionMode: SelectionMode) = CoinListFragment().apply{
            this.presenter = CoinListPresenter(wallet, isValid, selectionMode)
        }
    }
}
