package com.linkbit.android.presentation.wallet.manage.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View

import com.linkbit.android.R
import com.linkbit.android.entity.CoinModel
import com.linkbit.android.data.model.wallet.WalletEditModel
import com.linkbit.android.presentation.BaseActivity
import com.linkbit.android.adapter.coin.CoinListViewHolder
import com.linkbit.android.ui.base.SimpleTextChangeListener
import kotlinx.android.synthetic.main.activity_wallet_info_edit.*
import kotlinx.android.synthetic.main.fragment_create_wallet_step2.*
import kotlinx.android.synthetic.main.fragment_create_wallet_step3.*

class WalletInfoEditActivity : BaseActivity<WalletInfoEditPresenter>(), WalletInfoEditView {
    override val presenter: WalletInfoEditPresenter = WalletInfoEditPresenter(this, wallet, isValid)
    private lateinit var wallet: WalletEditModel
    private lateinit var isValid: (state: Boolean) -> Unit


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet_info_edit)
        this.presenter.init()
        wallet.let {
            layout_wallet_info_edit_coin_list.addView(createCoinItem(it.coin))
            et_wallet_info_edit_name.setText(it.name)
            et_wallet_info_edit_description.setText(it.description)
        }
        et_wallet_info_edit_password.addTextChangedListener(SimpleTextChangeListener({presenter.setPassword(it)}))
        et_wallet_info_edit_password_confirm.addTextChangedListener(SimpleTextChangeListener({presenter.setPasswordConfim(it)}))
        et_wallet_info_edit_name.addTextChangedListener(SimpleTextChangeListener{presenter.setName(it)})
        et_wallet_info_edit_description.addTextChangedListener(SimpleTextChangeListener{presenter.setDescription(it)})

  /*      // setting secure spinner adpater
        val spinnerItem = arrayOf("핀번호")
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerItem)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_wallet_info_edit_secure.adapter = adapter*/
    }

    private fun createCoinItem(coin: CoinModel): View {
        val view = LayoutInflater.from(this).inflate(R.layout.view_coin_item, null, false)
        val holder = CoinListViewHolder(view)
        holder.setIcon(coin.symbol)
        holder.setCoinText(coin.symbol, coin.name)
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(wallet: WalletEditModel, isValid: (state: Boolean) -> Unit) = WalletInfoEditActivity().apply{
            this.wallet = wallet
            this.isValid = isValid
        }
    }
}
