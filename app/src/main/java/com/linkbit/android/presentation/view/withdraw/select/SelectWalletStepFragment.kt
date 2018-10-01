package com.linkbit.android.presentation.view.wallet.list

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

import com.linkbit.android.R
import com.linkbit.android.presentation.adapter.wallet.WalletListAdapter
import com.linkbit.android.entity.WalletModel
import com.linkbit.android.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_withdraw_step1.view.*

class SelectWalletStepFragment : BaseFragment<SelectWalletStepPresenter>(), SelectWalletStepView {
    private lateinit var walletListAdapter: WalletListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_withdraw_step1, container, false)
        view.recyclerView_withdraw_select_wallet.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        view.recyclerView_withdraw_select_wallet.adapter = walletListAdapter
        return view

    }

    companion object {
        @JvmStatic
        fun newInstance(onSelect : (wallet: WalletModel?) -> Unit ) =
                SelectWalletStepFragment().apply {
                    this.walletListAdapter = WalletListAdapter(this.context, onSelect)
                }
    }
}
