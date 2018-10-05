package com.linkbit.android.presentation.view.wallet.list

import com.linkbit.android.data.repository.WalletRepository
import com.linkbit.android.presentation.base.Presenter

class WalletListPresenter(
        view: WalletListView,
        private val walletRepository: WalletRepository = WalletRepository(view.getContext())
) : Presenter<WalletListView>(view) {

    fun init() {
        disposables.add(walletRepository.getWalletList().subscribe({ view.setWalletItems(it) }, { /* @TODO Implement wallet list load error handling*/ }))
    }
}