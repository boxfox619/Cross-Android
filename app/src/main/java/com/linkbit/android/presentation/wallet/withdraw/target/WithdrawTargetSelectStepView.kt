package com.linkbit.android.presentation.wallet.list

import com.linkbit.android.entity.UserModel
import com.linkbit.android.presentation.View

interface WithdrawTargetSelectStepView : View {
    fun setFriendItems(friendList: List<UserModel>)
}