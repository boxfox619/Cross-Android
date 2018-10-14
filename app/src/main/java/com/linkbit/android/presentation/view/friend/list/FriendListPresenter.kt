package com.linkbit.android.presentation.view.friend.list

import com.linkbit.android.data.repository.FriendRepository
import com.linkbit.android.presentation.base.Presenter

class FriendListPresenter(
        view: FriendListView,
        private val friendRepository: FriendRepository = FriendRepository(view.getContext())
) : Presenter<FriendListView>(view) {

    fun loadFriendList() {
        this.friendRepository.getFriendList().subscribe{
            this.view.setFriendItems(it)
        }
    }

}