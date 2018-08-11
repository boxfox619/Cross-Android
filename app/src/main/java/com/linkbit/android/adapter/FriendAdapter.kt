package com.linkbit.android.adapter

import android.content.Context
import android.view.ViewGroup
import com.linkbit.android.data.model.User
import com.linkbit.android.ui.base.AbstractRecyclerAdapter
import com.linkbit.android.presentation.main.holder.FriendCardViewHolder

class FriendAdapter(context: Context) : AbstractRecyclerAdapter<User, FriendCardViewHolder>(context) {
    override fun onItemViewType(position: Int): Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendCardViewHolder {
        return FriendCardViewHolder(context, parent)
    }

    override fun onBindViewHolder(holder: FriendCardViewHolder, position: Int) {
        var model = getItem(position)
        holder.setName(model!!.name!!)
        holder.setAddress(model!!.crossAddress!!)
    }
}
