package com.linkbit.android.presentation.view.wallet.detail

import android.os.Bundle
import com.linkbit.android.R
import com.linkbit.android.entity.WalletModel
import com.linkbit.android.presentation.base.BaseActivity
import com.linkbit.android.presentation.trasnaction.list.TransactionListFragment
import kotlinx.android.synthetic.main.activity_wallet_detail.*
import kotlinx.android.synthetic.main.view_wallet_info_card.*

class WalletDetailActivity : BaseActivity<WalletDetailPresenter>(), WalletDetailView {

    override val presenter: WalletDetailPresenter = WalletDetailPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet_detail)
        val address = intent.getStringExtra("address")
        val fragmentManager = fragmentManager
        fragmentManager.beginTransaction().replace(R.id.layout_transaction_list_content, TransactionListFragment.newInstance(address)).commit()
        setSupportActionBar(toolbar_wallet_detail)
        supportActionBar!!.title = ""
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        toolbar_wallet_detail.setNavigationOnClickListener{finish()}
        this.presenter.init(address)
    }
    override fun initWalletInfo(walletModel: WalletModel) {
        tv_wallet_info_address.text = walletModel.accountAddress
        tv_wallet_info_linked_address.text = walletModel.linkbitAddress
        tv_wallet_info_balance.text = walletModel.balance.toString()
    }
}
