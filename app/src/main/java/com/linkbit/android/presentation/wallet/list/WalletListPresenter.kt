package com.linkbit.android.presentation.friend.list

import com.linkbit.android.data.repository.WalletRepository
import com.linkbit.android.presentation.Presenter

class WalletListPresenter(
        view: WalletListView,
        private val walletRepository: WalletRepository = WalletRepository(view.getContext())
) : Presenter<WalletListView>(view) {

    init {
        disposables.add(walletRepository.getWalletList().subscribe{view.setWalletItems(it)})
    }

}