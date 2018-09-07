package com.linkbit.android.presentation.wallet.create.finish

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.linkbit.android.R
import com.linkbit.android.helper.URLHelper
import com.linkbit.android.entity.WalletModel

class CreateWalletFinishFragment : Fragment() {
    lateinit var walletModel: WalletModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_create_wallet_finish, container, false)
        Glide.with(activity!!).load(URLHelper.createAssetUrl(activity!!, this.walletModel.coinSymbol.toUpperCase())).into(view.findViewById(R.id.iv_wallet_symbol_icon))
        view.findViewById<TextView>(R.id.tv_wallet_info_symbol).text = this.walletModel.coinSymbol
        view.findViewById<TextView>(R.id.tv_wallet_info_linked_address).text = this.walletModel.linkbitAddress
        view.findViewById<TextView>(R.id.tv_wallet_info_address).text = this.walletModel.accountAddress
        view.findViewById<TextView>(R.id.tv_wallet_info_wallet_name).text = this.walletModel.walletName
        view.findViewById<TextView>(R.id.tv_create_wallet_finish_desc).text = this.walletModel.description
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(walletModel: WalletModel) =
                CreateWalletFinishFragment().apply {
                    this.walletModel = walletModel
                }
    }
}
