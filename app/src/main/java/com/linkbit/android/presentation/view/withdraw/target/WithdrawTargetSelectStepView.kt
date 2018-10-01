package com.linkbit.android.presentation.view.wallet.list

import com.linkbit.android.entity.UserModel
import com.linkbit.android.presentation.base.View

interface WithdrawTargetSelectStepView : View {
    fun setFriendItems(friendList: List<UserModel>)
}