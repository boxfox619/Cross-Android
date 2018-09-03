package com.linkbit.android.presentation.wallet.manage.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner

import com.linkbit.android.R
import com.linkbit.android.entity.CoinModel
import com.linkbit.android.data.model.wallet.WalletEditModel
import com.linkbit.android.presentation.BaseFragment
import com.linkbit.android.presentation.wallet.manage.coinlist.CoinListViewHolder
import com.linkbit.android.ui.base.SimpleTextChangeListener
import kotlinx.android.synthetic.main.fragment_wallet_info_edit.*


class WalletInfoEditFragment : BaseFragment<WalletInfoEditPresenter>(), WalletInfoEditView {
    private lateinit var wallet: WalletEditModel
    private lateinit var isValid: (state: Boolean) -> Unit

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_wallet_info_edit, container, false)
        this.presenter = WalletInfoEditPresenter(this, wallet, isValid)
        this.presenter.init()
        wallet.let {
            view.findViewById<LinearLayout>(R.id.layout_wallet_info_edit_coin_list).addView(createCoinItem(it.coin))
            view.findViewById<EditText>(R.id.et_wallet_info_edit_name).setText(it.name)
            view.findViewById<EditText>(R.id.et_wallet_info_edit_description).setText(it.description)
        }
        view.findViewById<EditText>(R.id.et_wallet_info_edit_password).addTextChangedListener(SimpleTextChangeListener({presenter.setPassword(it)}))
        view.findViewById<EditText>(R.id.et_wallet_info_edit_password_confirm).addTextChangedListener(SimpleTextChangeListener({presenter.setPasswordConfim(it)}))
        view.findViewById<EditText>(R.id.et_wallet_info_edit_name).addTextChangedListener(SimpleTextChangeListener{presenter.setName(it)})
        view.findViewById<EditText>(R.id.et_wallet_info_edit_description).addTextChangedListener(SimpleTextChangeListener{presenter.setDescription(it)})

        // setting secure spinner adpater
        val spinnerItem = arrayOf("핀번호")
        val adapter = ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, spinnerItem)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        view.findViewById<Spinner>(R.id.spinner_wallet_info_edit_secure).adapter = adapter
        return view
    }

    private fun createCoinItem(coin: CoinModel): View {
        val view = LayoutInflater.from(context).inflate(R.layout.view_coin_item, null, false)
        val holder = CoinListViewHolder(view)
        holder.setIcon(coin.symbol)
        holder.setCoinText(coin.symbol, coin.name)
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(wallet: WalletEditModel, isValid: (state: Boolean) -> Unit) = WalletInfoEditFragment().apply{
            this.wallet = wallet
            this.isValid = isValid
        }
    }
}
