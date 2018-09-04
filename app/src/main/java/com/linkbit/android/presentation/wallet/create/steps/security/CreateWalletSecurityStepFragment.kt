package com.linkbit.android.presentation.wallet.list

import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText

import com.linkbit.android.R
import com.linkbit.android.data.model.wallet.WalletEditModel
import com.linkbit.android.presentation.BaseFragment
import com.linkbit.android.ui.base.SimpleTextChangeListener

class CreateWalletSecurityStepFragment : BaseFragment<CreateWalletSecurityStepPresenter>(), CreateWalletSecurityStepView {
    private lateinit var et_layout_info_edit_password : TextInputLayout
    private lateinit var et_layout_info_edit_password_confirm : TextInputLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_create_wallet_step3, container, false)

        view.findViewById<EditText>(R.id.et_wallet_info_edit_password).addTextChangedListener(SimpleTextChangeListener {presenter.setPassword(it)})
        view.findViewById<EditText>(R.id.et_wallet_info_edit_password_confirm).addTextChangedListener(SimpleTextChangeListener {presenter.setPasswordConfirm(it)})
        this.et_layout_info_edit_password = view.findViewById(R.id.et_layout_info_edit_password)
        this.et_layout_info_edit_password_confirm = view.findViewById(R.id.et_layout_info_edit_password_confirm)
        return view
    }
    override fun setPasswordInputError(msg: String) {
        this.et_layout_info_edit_password.error = msg
    }

    override fun setPasswordConfirmInputError(msg: String) {
        this.et_layout_info_edit_password_confirm.error = msg
    }

    companion object {
        @JvmStatic
        fun newInstance(walletEditModel : WalletEditModel, isValid : (valid: Boolean) -> Unit ) =
                CreateWalletSecurityStepFragment().apply {
                    this.presenter = CreateWalletSecurityStepPresenter(this, walletEditModel, isValid)
                }
    }
}
