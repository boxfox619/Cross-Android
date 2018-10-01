package com.linkbit.android.presentation.view.wallet.list

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

import com.linkbit.android.R
import com.linkbit.android.presentation.adapter.SelectionMode
import com.linkbit.android.presentation.adapter.wallet.WalletListAdapter
import com.linkbit.android.entity.WalletModel
import com.linkbit.android.presentation.base.BaseFragment

class WalletListFragment : BaseFragment<WalletListPresenter>(), WalletListView {
    lateinit var walletListAdpater : WalletListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_any_list, container, false)
        view.findViewById<RecyclerView>(R.id.recyclerview_any).layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
        view.findViewById<RecyclerView>(R.id.recyclerview_any).adapter = walletListAdpater
        this.presenter = WalletListPresenter(this)
        presenter.init()
        return view
    }

    override fun setWalletItems(items: List<WalletModel>) {
        walletListAdpater.clear()
        walletListAdpater.addItems(items)
    }

    companion object {
        @JvmStatic
        fun newInstance(context: Context) = WalletListFragment().apply {
            this.walletListAdpater = WalletListAdapter(context)
        }

        @JvmStatic
        fun newInstance(context: Context, listener: (wallet: WalletModel) -> Unit) = WalletListFragment().apply {
            this.walletListAdpater = WalletListAdapter(context, listener, SelectionMode.SINGLE)
        }
    }
}
