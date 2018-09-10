package com.linkbit.android.presentation.wallet.list

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.linkbit.android.R
import com.linkbit.android.presentation.BaseFragment
import com.linkbit.android.ui.base.SimpleTextChangeListener
import kotlinx.android.synthetic.main.fragment_withdraw_step3.view.*
import android.widget.TextView
import android.widget.TabHost
import com.linkbit.android.adapter.wallet.WalletCardViewHolder
import com.linkbit.android.entity.WalletModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_withdraw_step2.view.*


class AmountStepFragment : BaseFragment<AmountStepPresenter>(), AmountStepView {
    private lateinit var rootView: View
    private lateinit var tabHost: TabHost

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        this.rootView = inflater.inflate(R.layout.fragment_withdraw_step3, container, false)
        this.rootView.et_withdraw_step3_amount.addTextChangedListener(SimpleTextChangeListener { presenter.setAmount(it) })
        this.tabHost = this.rootView.tabhost_withdraw_step2
        this.presenter.init()
        return this.rootView
    }

    override fun setSoruceWalletInfo(wallet: WalletModel) {
        val holder = WalletCardViewHolder(this.rootView)
        holder.init(wallet)
        this.rootView.tv_withdraw_step3_balance_symbol.text = wallet.coinSymbol
        this.rootView.tv_withdraw_step3_balance.text = wallet.balance.toString()

    }

    override fun addTabSpec(tabName: String, label: String, iconId: Int) {
        val spec = tab_host.newTabSpec(tabName)
        val tab = TextView(context)
        tab.text = label
        tab.setCompoundDrawablesWithIntrinsicBounds(0, iconId, 0, 0)
        spec.setIndicator(tab)
        tab_host.addTab(spec)
    }

    companion object {
        @JvmStatic
        fun newInstance(sourceAddress: String, onEnter: (amount: Double) -> Unit) =
                AmountStepFragment().apply {
                    this.presenter = AmountStepPresenter(this, sourceAddress, onEnter)
                }
    }
}
