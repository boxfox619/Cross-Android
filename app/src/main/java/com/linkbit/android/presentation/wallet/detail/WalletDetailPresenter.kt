package com.linkbit.android.presentation.wallet.detail

import com.linkbit.android.data.repository.WalletRepository
import com.linkbit.android.presentation.Presenter

class WalletDetailPresenter(view: WalletDetailView,
                            private val walletRepository: WalletRepository = WalletRepository(view.getContext())
) : Presenter<WalletDetailView>(view) {

    fun init(address: String) {
        this.walletRepository.getWalletByAddress(address).subscribe{
            this.view.initWalletInfo(it)
        }
    }

}