package com.linkbit.android.ui.fragment

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.linkbit.android.R
import com.linkbit.android.model.Wallet
import com.linkbit.android.presenter.WalletInfoEditPresenter
import com.linkbit.android.ui.view.WalletInfoEditView

class WalletInfoEditFragment : Fragment(), WalletInfoEditView {
    lateinit var wallet: Wallet
    lateinit var confirmListener: (wallet: Wallet) -> Unit
    lateinit var walletInfoEditPresenter: WalletInfoEditPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_wallet_info_edit, container, false)
        this.walletInfoEditPresenter = WalletInfoEditPresenter()
        this.walletInfoEditPresenter.addView(this)
        this.walletInfoEditPresenter.wallet = wallet
        return view
    }

    override fun getContext(): Context {
        return super.getContext()!!
    }

    companion object {
        @JvmStatic
        fun newInstance(wallet: Wallet, confirmListener: (Any) -> Unit) =
                WalletInfoEditFragment().apply {
                    this.wallet = wallet
                    this.confirmListener = confirmListener
                }
    }
}
