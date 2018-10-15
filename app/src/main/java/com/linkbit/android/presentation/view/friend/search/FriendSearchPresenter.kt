package com.linkbit.android.presentation.view.friend.search

import com.linkbit.android.data.repository.FriendRepository
import com.linkbit.android.helper.ToastHelper
import com.linkbit.android.presentation.base.Presenter

class FriendSearchPresenter(
        view: FriendSearchView,
        private val friendRepository: FriendRepository = FriendRepository(view.getContext())
) : Presenter<FriendSearchView>(view) {

    fun searchFriend(text: String, page: Int, count: Int = 10) {
        this.friendRepository.searchUser(text, page, count).subscribe({
            view.addFriendItems(it)
        }, {
            ToastHelper.showToast(view.getContext(), it.message!!)
        })
    }
}