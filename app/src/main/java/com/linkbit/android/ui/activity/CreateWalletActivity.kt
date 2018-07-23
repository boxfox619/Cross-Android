package com.linkbit.android.ui.activity

import android.app.Fragment
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.linkbit.android.R
import com.linkbit.android.model.Wallet
import com.linkbit.android.model.WalletEditModel
import com.linkbit.android.presenter.CreateWalletPresenter
import com.linkbit.android.ui.fragment.CoinListFragment
import com.linkbit.android.ui.fragment.WalletInfoEditFragment
import com.linkbit.android.ui.view.CreateWalletView
import kotlinx.android.synthetic.main.activity_create_wallet.*

class CreateWalletActivity : AppCompatActivity(), CreateWalletView {

    lateinit var presenter: CreateWalletPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_wallet)

        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.title = getString(R.string.activity_name_create_wallet)
        presenter = CreateWalletPresenter()
        presenter.addView(this)
        btn_createwallet_next.setOnClickListener { presenter.onNext() }
    }

    override fun getContext(): Context {
        return this
    }

    override fun setStep(step: Int) {
        var fragment: Fragment? = null
        when (step) {
            0 -> fragment = CoinListFragment.newInstance { presenter.baseCoinUpdate(it) }
            1 -> fragment = CoinListFragment.newInstance { presenter.subCoinUpdate(it) }
            2 -> fragment = WalletInfoEditFragment.newInstance(presenter.wallet, { presenter.walletUpdate(it as WalletEditModel) })
        }
        if (fragment != null) {
            val ft = fragmentManager.beginTransaction()
            ft.add(R.id.framelayout_content_view, fragment).commit()
        }
    }
}
