package com.linkbit.android.presentation.wallet.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide

import com.linkbit.android.R
import com.linkbit.android.entity.TransactionModel
import com.linkbit.android.helper.URLHelper
import com.linkbit.android.presentation.BaseFragment
import kotlinx.android.synthetic.main.fragment_withdraw_step4.view.*
import kotlinx.android.synthetic.main.view_simple_wallet_card.view.*

class WithdrawResultStepFragment : BaseFragment<WithdrawResultStepPresenter>(), WithdrawResultStepView {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_withdraw_step4, container, false)
        this.presenter.init()
        return view
    }

    override fun initWithdrawResult(result: TransactionModel, remainBalance: Double) {
        this.view.tv_withdraw_step4_symbol.text = result.symbol
        this.view.tv_withdraw_step4_symbol2.text = result.symbol
        this.view.tv_withdraw_step4_amount.text = result.amount.toString()
        this.view.tv_withdraw_step4_balance.text = remainBalance.toString()
        this.view.tv_withdraw_step4_date.text = result.date
        this.view.tv_simple_wallet_label.text = result.targetProfile
        this.view.tv_simple_wallet_address.text = result.targetAddress
        val url = URLHelper.createAssetUrl(context, result.symbol)
        Glide.with(context).load(url).into(this.view.iv_simple_wallet_icon)
    }

    companion object {
        @JvmStatic
        fun newInstance(remainBalance: Double, result: TransactionModel) =
                WithdrawResultStepFragment().apply {
                    this.presenter = WithdrawResultStepPresenter(this, remainBalance, result)
                }
    }
}
