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
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_withdraw_step2.view.*


class AmountStepFragment : BaseFragment<AmountStepPresenter>(), AmountStepView {
    private var currentBalance: Double = 0.toDouble()
    private lateinit var tabHost: TabHost

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_withdraw_step3, container, false)
        view.et_withdraw_step3_amount.addTextChangedListener(SimpleTextChangeListener { presenter.setAmount(it) })
        //view.et_withdraw_step3_amount.setText(currentBalance.toString())
        this.tabHost = view.tabhost_withdraw_step2
        return view

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
        fun newInstance(currentBalance: Double, onEnter: (amount: Double) -> Unit) =
                AmountStepFragment().apply {
                    this.currentBalance = currentBalance
                    this.presenter = AmountStepPresenter(this, onEnter)
                }
    }
}
