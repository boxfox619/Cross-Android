package com.linkbit.android.presentation.wallet.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText

import com.linkbit.android.R
import com.linkbit.android.data.model.wallet.WalletEditModel
import com.linkbit.android.entity.WalletModel
import com.linkbit.android.presentation.BaseFragment
import com.linkbit.android.ui.base.SimpleTextChangeListener

class CreateWalletInfoStepFragment : BaseFragment<CreateWalletInfoStepPresenter>(), CreateWalletInfoStepView {
    lateinit var walletListAdpater : WalletListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_create_wallet_step2, container, false)
        view.findViewById<EditText>(R.id.et_wallet_info_edit_name).addTextChangedListener(SimpleTextChangeListener {presenter.setName(it)})
        view.findViewById<EditText>(R.id.et_wallet_info_edit_description).addTextChangedListener(SimpleTextChangeListener {presenter.setDescription(it)})
        return view
    }

    override fun setWalletItems(items: List<WalletModel>) {
        walletListAdpater.clear()
        walletListAdpater.addItems(items)
    }

    companion object {
        @JvmStatic
        fun newInstance(walletEditModel : WalletEditModel, isValid : (valid: Boolean) -> Unit ) =
                CreateWalletInfoStepFragment().apply {
                    this.presenter = CreateWalletInfoStepPresenter(this, walletEditModel, isValid)
                }
    }
}
