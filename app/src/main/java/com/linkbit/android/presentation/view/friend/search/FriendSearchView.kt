package com.linkbit.android.presentation.view.friend.search

import com.linkbit.android.entity.UserModel
import com.linkbit.android.presentation.base.View

interface FriendSearchView : View {
    fun addFriendItems(items: List<UserModel>): Unit
}