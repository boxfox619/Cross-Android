package com.linkbit.android.presentation.view.wallet.detail

import com.linkbit.android.R
import com.linkbit.android.data.repository.WalletRepository
import com.linkbit.android.helper.ToastHelper
import com.linkbit.android.presentation.base.Presenter

class WalletDetailPresenter(view: WalletDetailView,
                            private val walletRepository: WalletRepository = WalletRepository(view.getContext())
) : Presenter<WalletDetailView>(view) {

    fun init(address: String) {
        this.walletRepository.getWalletByAddress(address).subscribe({
            this.view.initWalletInfo(it)
        },{
            ToastHelper.showToast(view.getContext(), R.string.err_wallet_detail_load_fail)
            //@TODO implement the wallet detail load error handler
        })
    }

}