package com.linkbit.android.presentation.wallet.list

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

import com.linkbit.android.R
import com.linkbit.android.adapter.wallet.WalletListAdapter
import com.linkbit.android.presentation.BaseFragment

class SelectWalletStepFragment : BaseFragment<SelectWalletStepPresenter>(), SelectWalletStepView {
    private lateinit var walletListAdapter: WalletListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_withdraw_step1, container, false)
        view.findViewById<RecyclerView>(R.id.recyclerView_withdraw_select_wallet).layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        view.findViewById<RecyclerView>(R.id.recyclerView_withdraw_select_wallet).adapter = walletListAdapter
        return view

    }

    companion object {
        @JvmStatic
        fun newInstance(onSelect : (address: String?) -> Unit ) =
                SelectWalletStepFragment().apply {
                    this.walletListAdapter = WalletListAdapter(this.context, onSelect)
                }
    }
}
