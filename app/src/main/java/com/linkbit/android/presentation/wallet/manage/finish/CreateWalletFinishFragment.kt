package com.linkbit.android.presentation.wallet.manage.finish

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.linkbit.android.R
import com.linkbit.android.helper.URLHelper
import com.linkbit.android.model.Wallet
import kotlinx.android.synthetic.main.fragment_create_wallet_finish.*

class CreateWalletFinishFragment : Fragment() {
    lateinit var wallet: Wallet
    lateinit var confirmListener: () -> Unit

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_create_wallet_finish, container, false)
        Glide.with(activity!!).load(URLHelper.createAssetUrl(activity!!, this.wallet.coin.toUpperCase())).into(view.findViewById(R.id.iv_wallet_icon))
        view.findViewById<TextView>(R.id.tv_wallet_symbol).text = this.wallet.coin
        view.findViewById<TextView>(R.id.tv_wallet_cross_address).text = this.wallet.crossAddress
        view.findViewById<TextView>(R.id.tv_wallet_address).text = this.wallet.originalAddress
        btn_createwallet_finish.setOnClickListener({this.confirmListener()})
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(wallet: Wallet, confirmListener: () -> Unit) =
                CreateWalletFinishFragment().apply {
                    this.wallet = wallet
                    this.confirmListener = confirmListener
                }
    }
}
