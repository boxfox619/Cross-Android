package com.linkbit.android.presentation.wallet.list

import com.linkbit.android.data.repository.FriendRepository
import com.linkbit.android.presentation.Presenter

class WithdrawTargetSelectStepPresenter(
        view: WithdrawTargetSelectStepView,
        private val listener: (address: String?) -> Unit,
        private val friendRepository: FriendRepository = FriendRepository(view.getContext())
) : Presenter<WithdrawTargetSelectStepView>(view) {
    fun init(){
        this.friendRepository.getFriendList().subscribe{ view.setFriendItems(it) }
    }

    fun tabChanged(){
        listener(null)
    }

    fun setTargetAddress(address: String){
        //@TODO address validation
        listener(address)
    }
}