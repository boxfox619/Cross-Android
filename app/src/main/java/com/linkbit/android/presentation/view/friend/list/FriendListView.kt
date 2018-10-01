package com.linkbit.android.presentation.view.friend.list

import com.linkbit.android.entity.UserModel
import com.linkbit.android.presentation.base.View

interface FriendListView : View {
    fun setFriendItems(items: List<UserModel>): Unit

}