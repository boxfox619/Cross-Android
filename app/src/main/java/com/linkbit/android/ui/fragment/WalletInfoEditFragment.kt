package com.linkbit.android.ui.fragment

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

import com.linkbit.android.R
import com.linkbit.android.helper.URLHelper
import com.linkbit.android.model.Coin
import com.linkbit.android.model.Wallet
import com.linkbit.android.presenter.WalletInfoEditPresenter
import com.linkbit.android.ui.view.WalletInfoEditView
import kotlinx.android.synthetic.main.fragment_wallet_info_edit.*

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

    fun initView(){
        layout_createwallet_coin_list.addView(createCoinItem(wallet.coinSymbol))
        wallet.subCoinSymbolList.forEach({
            layout_createwallet_coin_list.addView(createCoinItem(it))
        })
    }
    fun createCoinItem(symbol: String): View {
        var view = LayoutInflater.from(getContext()).inflate(R.layout.view_wallet_info_card, null, false)
        val url = URLHelper.createAssetUrl(getContext(), symbol)
        //@TODO coin symbol & name
        Glide.with(getContext()).load(url).into(view.findViewById<ImageView>(R.id.iv_coin_icon))
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
