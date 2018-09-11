package com.linkbit.android.presentation.wallet.create

import android.app.Fragment
import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.linkbit.android.R
import com.linkbit.android.adapter.SelectionMode
import com.linkbit.android.helper.ProgressDialogHelper
import com.linkbit.android.presentation.BaseActivity
import com.linkbit.android.presentation.coin.list.CoinListFragment
import com.linkbit.android.presentation.wallet.create.finish.CreateWalletFinishFragment
import com.linkbit.android.presentation.wallet.list.*
import kotlinx.android.synthetic.main.activity_create_wallet.*

class WithdrawActivity : BaseActivity<WithdrawPresenter>(), WithdrawView {
    override val presenter: WithdrawPresenter = WithdrawPresenter(this)
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_wallet)
        setSupportActionBar(toolbar_create_wallet)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = getString(R.string.activity_name_create_wallet)
        presenter.init()
        btn_createwallet_next.setOnClickListener { presenter.onNext() }
    }

    override fun getContext(): Context {
        return this
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun nextButtonEnabled(state: Boolean) {
        if(state){
            btn_createwallet_next.visibility = View.VISIBLE
        }else{
            btn_createwallet_next.visibility = View.INVISIBLE
        }
    }

    override fun setStep(step: Int) {
        var fragment: Fragment? = null
        when (step) {
            0 -> fragment = SelectWalletStepFragment.newInstance { presenter.sourceWalletSelected(it) }
            1 -> fragment = WithdrawTargetSelectStepFragment.newInstance { presenter.targetWalletSelected(it) }
            2 -> fragment = AmountStepFragment.newInstance(presenter.sourceWallet!!, presenter.targetWallet!!) { presenter.amountSelected(it) }
            3 -> presenter.doWithdraw()
            4 -> fragment =WithdrawResultStepFragment.newInstance(presenter.remainBalance, presenter.transactionResult)
        }
        if (fragment != null) {
            val ft = fragmentManager.beginTransaction()
            ft.replace(R.id.framelayout_withdraw_content_view, fragment).commit()
        }
    }

    override fun setProgressDialogVisible(visible: Boolean) {
        if(visible){
            progressDialog = ProgressDialogHelper.show(this, "", "송금 요청중...", false)
        }else{
            progressDialog.dismiss()
        }
    }
}




