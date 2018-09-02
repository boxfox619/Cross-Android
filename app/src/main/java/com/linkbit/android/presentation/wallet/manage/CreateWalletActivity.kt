package com.linkbit.android.presentation.wallet.manage

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import com.linkbit.android.R
import com.linkbit.android.presentation.wallet.manage.coinlist.SelectionMode
import com.linkbit.android.presentation.BaseActivity
import com.linkbit.android.presentation.wallet.manage.coinlist.CoinListFragment
import com.linkbit.android.presentation.wallet.manage.finish.CreateWalletFinishFragment
import com.linkbit.android.presentation.wallet.manage.info.WalletInfoEditFragment
import kotlinx.android.synthetic.main.activity_create_wallet.*

class CreateWalletActivity : BaseActivity<CreateWalletPresenter>(), CreateWalletView {
    override val presenter: CreateWalletPresenter = CreateWalletPresenter(this)

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
        btn_createwallet_next.isEnabled = state
    }

    override fun setStep(step: Int) {
        var fragment: Fragment? = null
        when (step) {
            0 -> fragment = CoinListFragment.newInstance (presenter.supportedCoins, presenter.wallet, { presenter.canNext(it) }, SelectionMode.SINGLE)
            1 -> fragment = WalletInfoEditFragment.newInstance(presenter.wallet, { presenter.canNext(it) })
            2 -> presenter.doCreate()
            3 -> fragment = CreateWalletFinishFragment.newInstance(presenter.resultWallet, {})
        }
        if (fragment != null) {
            val ft = fragmentManager.beginTransaction()
            ft.add(R.id.framelayout_content_view, fragment).commit()
        }
    }
}
