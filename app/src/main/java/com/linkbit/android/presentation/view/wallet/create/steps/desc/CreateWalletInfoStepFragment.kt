package com.linkbit.android.presentation.view.wallet.list

import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.linkbit.android.R
import com.linkbit.android.data.model.wallet.WalletEditModel
import com.linkbit.android.presentation.base.BaseFragment
import com.linkbit.android.presentation.SimpleTextChangeListener
import kotlinx.android.synthetic.main.fragment_create_wallet_step2.view.*

class CreateWalletInfoStepFragment : BaseFragment<CreateWalletInfoStepPresenter>(), CreateWalletInfoStepView {
    private lateinit var rootView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_create_wallet_step2, container, false)
        rootView.et_wallet_info_edit_name.addTextChangedListener(SimpleTextChangeListener { presenter.setName(it) })
        rootView.et_wallet_info_edit_description.addTextChangedListener(SimpleTextChangeListener { presenter.setDescription(it) })
        return rootView
    }

    override fun setNameInputError(msg: String?) {
        if (msg == null) {
            this.rootView.et_layout_info_edit_name.isErrorEnabled = false
        } else {
            this.rootView.et_layout_info_edit_name.isErrorEnabled = true
            this.rootView.et_layout_info_edit_name.error = msg
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(walletEditModel: WalletEditModel, isValid: (valid: Boolean) -> Unit) =
                CreateWalletInfoStepFragment().apply {
                    this.presenter = CreateWalletInfoStepPresenter(this, walletEditModel, isValid)
                }
    }
}
