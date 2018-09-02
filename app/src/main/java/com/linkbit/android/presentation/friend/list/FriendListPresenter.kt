package com.linkbit.android.presentation.friend.list

import com.linkbit.android.data.repository.FriendRepository
import com.linkbit.android.presentation.Presenter

class FriendListPresenter(
        view: FriendListView,
        private val friendRepository: FriendRepository = FriendRepository(view.getContext())
) : Presenter<FriendListView>(view) {

    fun loadFriendList() {
        this.friendRepository.getFriendList().subscribe{
            this.view.setFriendItems(it)
        }
    }

    override fun destory() {
        super.destory()
    }

}