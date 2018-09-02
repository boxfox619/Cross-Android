package com.linkbit.android.presentation.wallet.manage.info

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide

import com.linkbit.android.R
import com.linkbit.android.helper.URLHelper
import com.linkbit.android.entity.CoinModel
import com.linkbit.android.data.model.wallet.WalletEditModel
import com.linkbit.android.presentation.BaseFragment
import com.linkbit.android.ui.base.SimpleTextChangeListener
import kotlinx.android.synthetic.main.fragment_wallet_info_edit.*


class WalletInfoEditFragment : BaseFragment<WalletInfoEditPresenter>(), WalletInfoEditView {
    private lateinit var wallet: WalletEditModel
    private lateinit var isValid: (state: Boolean) -> Unit

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_wallet_info_edit, container, false)
        this.presenter = WalletInfoEditPresenter(this, wallet, isValid)
        return view
    }

    override fun initView(wallet: WalletEditModel){
        wallet.let {
            layout_wallet_info_edit_coin_list.addView(createCoinItem(it.coin))
            et_wallet_info_edit_name.setText(it.name)
            et_wallet_info_edit_description.setText(it.description)
        }
        et_wallet_info_edit_password.addTextChangedListener(SimpleTextChangeListener({presenter.setPassword(it)}))
        et_wallet_info_edit_password_confirm.addTextChangedListener(SimpleTextChangeListener({presenter.setPasswordConfim(it)}))
        et_wallet_info_edit_name.addTextChangedListener(SimpleTextChangeListener{presenter.setName(it)})
        et_wallet_info_edit_description.addTextChangedListener(SimpleTextChangeListener{presenter.setDescription(it)})
    }

    fun createCoinItem(coin: CoinModel): View {
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
        fun newInstance(wallet: WalletEditModel, isValid: (state: Boolean) -> Unit) = WalletInfoEditFragment().apply{
            this.wallet = wallet
            this.isValid = isValid
        }
    }
}
