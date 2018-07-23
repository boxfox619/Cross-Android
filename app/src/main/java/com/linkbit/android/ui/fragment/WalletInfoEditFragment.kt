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
import com.linkbit.android.model.WalletEditModel
import com.linkbit.android.presenter.WalletInfoEditPresenter
import com.linkbit.android.ui.view.WalletInfoEditView
import kotlinx.android.synthetic.main.fragment_wallet_info_edit.*
import com.linkbit.android.ui.base.SimpleTextChangeListener


class WalletInfoEditFragment : Fragment(), WalletInfoEditView {
    lateinit var walletInfoEditPresenter: WalletInfoEditPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_wallet_info_edit, container, false)
        this.walletInfoEditPresenter.addView(this)
        btn_wallet_info_edit.setOnClickListener({onFinish()})
        return view
    }

    override fun initView(wallet: WalletEditModel){
        wallet.let({
            layout_wallet_info_edit_coin_list.addView(createCoinItem(it.coin))
            it.subCoinList.forEach({
                layout_wallet_info_edit_coin_list.addView(createCoinItem(it))
            })
            et_wallet_info_edit_name.setText(it.name)
            et_wallet_info_edit_description.setText(it.description)
        })
        et_wallet_info_edit_password.addTextChangedListener(SimpleTextChangeListener({walletInfoEditPresenter.setPassword(it)}))
        et_wallet_info_edit_password_confirm.addTextChangedListener(SimpleTextChangeListener({walletInfoEditPresenter.setPasswordConfim(it)}))
    }

    fun onFinish() {
        val name = et_wallet_info_edit_name.text.toString()
        val desc = et_wallet_info_edit_description.text.toString()
        walletInfoEditPresenter.onFinish(name, desc)
    }

    fun createCoinItem(coin: Coin): View {
        var view = LayoutInflater.from(getContext()).inflate(R.layout.view_wallet_info_card, null, false)
        val url = URLHelper.createAssetUrl(getContext(), coin.symbol)
        view.findViewById<TextView>(R.id.tv_coin_desc).text = String.format("%s - %s", coin.symbol, coin.name)
        Glide.with(getContext()).load(url).into(view.findViewById(R.id.iv_coin_icon))
        return view
    }

    override fun getContext(): Context {
        return super.getContext()!!
    }

    companion object {
        @JvmStatic
        fun newInstance(wallet: WalletEditModel, confirmListener: (Any) -> Unit) =
                WalletInfoEditFragment().apply {
                    walletInfoEditPresenter = WalletInfoEditPresenter(wallet, confirmListener)
                }
    }
}
