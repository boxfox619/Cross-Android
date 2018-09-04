package com.linkbit.android.presentation.wallet.create

import android.app.Fragment
import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.linkbit.android.R
import com.linkbit.android.presentation.coin.list.SelectionMode
import com.linkbit.android.presentation.BaseActivity
import com.linkbit.android.presentation.coin.list.CoinListFragment
import com.linkbit.android.presentation.wallet.create.finish.CreateWalletFinishFragment
import com.linkbit.android.presentation.wallet.list.CreateWalletInfoStepFragment
import com.linkbit.android.presentation.wallet.list.CreateWalletSecurityStepFragment
import com.linkbit.android.presentation.wallet.manage.info.WalletInfoEditActivity
import kotlinx.android.synthetic.main.activity_create_wallet.*

class CreateWalletActivity : BaseActivity<CreateWalletPresenter>(), CreateWalletView {
    override val presenter: CreateWalletPresenter = CreateWalletPresenter(this)
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
            0 -> fragment = CoinListFragment.newInstance (presenter.supportedCoins, presenter.wallet, { presenter.canNext(it) }, SelectionMode.SINGLE)
            1 -> fragment = CreateWalletInfoStepFragment.newInstance(presenter.wallet) { presenter.canNext(it) }
            2 -> fragment = CreateWalletSecurityStepFragment.newInstance(presenter.wallet) { presenter.canNext(it) }
            3 -> presenter.doCreate()
            4 -> fragment = CreateWalletFinishFragment.newInstance(presenter.resultWallet)
        }
        if (fragment != null) {
            val ft = fragmentManager.beginTransaction()
            ft.replace(R.id.framelayout_content_view, fragment).commit()
        }
    }

    override fun setProgressDialogVisible(visible: Boolean) {
        if(visible){
            progressDialog = ProgressDialog(this)
            progressDialog.setTitle("")
            progressDialog.setMessage("지갑 생성중...")
            progressDialog.setCancelable(false)
            progressDialog.show()
        }else{
            progressDialog.dismiss()
        }
    }
}
