package com.linkbit.android.presentation.wallet.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.linkbit.android.R
import com.linkbit.android.entity.WalletModel
import com.linkbit.android.presentation.BaseFragment
import kotlinx.android.synthetic.main.fragment_any_list.*

class WalletListFragment : BaseFragment<WalletListPresenter>(), WalletListView {
    override val presenter: WalletListPresenter = WalletListPresenter(this)
    lateinit var walletListAdpater : WalletListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_any_list, container, false)
        this.walletListAdpater = WalletListAdapter(this.context)
        recyclerview_any.adapter = walletListAdpater
        return view
    }

    override fun onDetach() {
        super.onDetach()
        presenter.destory()
    }

    override fun setWalletItems(items: List<WalletModel>) {
        walletListAdpater.clear()
        walletListAdpater.addItems(items)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                WalletListFragment()
    }
}
