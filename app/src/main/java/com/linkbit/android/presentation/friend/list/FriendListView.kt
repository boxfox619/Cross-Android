package com.linkbit.android.presentation.friend.list

import com.linkbit.android.entity.UserModel
import com.linkbit.android.presentation.View

interface FriendListView : View {
    fun setFriendItems(items: List<UserModel>): Unit

}