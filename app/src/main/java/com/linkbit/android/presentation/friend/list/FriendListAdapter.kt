package com.linkbit.android.presentation.friend.list

import android.content.Context
import android.view.ViewGroup
import com.linkbit.android.entity.UserModel
import com.linkbit.android.ui.base.AbstractRecyclerAdapter

class FriendListAdapter(context: Context) : AbstractRecyclerAdapter<UserModel, FriendCardViewHolder>(context) {
    override fun onItemViewType(position: Int): Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendCardViewHolder {
        return FriendCardViewHolder(context, parent)
    }

    override fun onBindViewHolder(holder: FriendCardViewHolder, position: Int) {
        var model = getItem(position)
        holder.setName(model!!.name!!)
        holder.setAddress(model!!.linkbitAddress!!)
    }
}
