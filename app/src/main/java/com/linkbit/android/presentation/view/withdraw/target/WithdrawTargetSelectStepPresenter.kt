package com.linkbit.android.presentation.view.wallet.list

import com.linkbit.android.data.repository.FriendRepository
import com.linkbit.android.data.repository.WalletRepository
import com.linkbit.android.entity.WalletModel
import com.linkbit.android.presentation.base.Presenter

class WithdrawTargetSelectStepPresenter(
        view: WithdrawTargetSelectStepView,
        private val listener: (wallet: WalletModel?) -> Unit,
        private val friendRepository: FriendRepository = FriendRepository(view.getContext()),
        private val walletRepository: WalletRepository = WalletRepository(view.getContext())
) : Presenter<WithdrawTargetSelectStepView>(view) {
    fun init() {
        this.friendRepository.getFriendList().subscribe { view.setFriendItems(it) }
    }

    fun tabChanged() {
        listener(null)
    }

    fun setTargetAddress(address: String) {
        //@TODO address validation
        this.walletRepository.loadWalletByAddress(address).subscribe({
            listener(it)
        }, {
            listener(null)
        })
    }
}