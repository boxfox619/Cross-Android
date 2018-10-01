package com.linkbit.android.presentation.view.wallet.list

import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText

import com.linkbit.android.R
import com.linkbit.android.data.model.wallet.WalletEditModel
import com.linkbit.android.presentation.base.BaseFragment
import com.linkbit.android.presentation.SimpleTextChangeListener

class CreateWalletInfoStepFragment : BaseFragment<CreateWalletInfoStepPresenter>(), CreateWalletInfoStepView {
    private lateinit var et_layout_info_edit_name : TextInputLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_create_wallet_step2, container, false)
        view.findViewById<EditText>(R.id.et_wallet_info_edit_name).addTextChangedListener(SimpleTextChangeListener { presenter.setName(it) })
        view.findViewById<EditText>(R.id.et_wallet_info_edit_description).addTextChangedListener(SimpleTextChangeListener { presenter.setDescription(it) })
        this.et_layout_info_edit_name = view.findViewById<TextInputLayout>(R.id.et_layout_info_edit_name)
        return view
    }
    override fun setNameInputError(msg: String?) {
        if(msg == null){
            this.et_layout_info_edit_name.isErrorEnabled = false
        }else{
            this.et_layout_info_edit_name.isErrorEnabled = true
            this.et_layout_info_edit_name.error = msg
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(walletEditModel : WalletEditModel, isValid : (valid: Boolean) -> Unit ) =
                CreateWalletInfoStepFragment().apply {
                    this.presenter = CreateWalletInfoStepPresenter(this, walletEditModel, isValid)
                }
    }
}
