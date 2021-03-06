package com.linkbit.android.presentation.adapter.friend

import android.content.Context
import android.view.ViewGroup
import com.linkbit.android.entity.UserModel
import com.linkbit.android.presentation.adapter.AbstractRecyclerAdapter

class FriendListAdapter(context: Context, private val onSelectListener: ((uid: String) -> Unit)?) : AbstractRecyclerAdapter<UserModel, FriendCardViewHolder>(context) {
    override fun onItemViewType(position: Int): Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendCardViewHolder {
        return FriendCardViewHolder(context, parent)
    }

    override fun onBindViewHolder(holder: FriendCardViewHolder, position: Int) {
        var model = getItem(position)
        holder.setProfile(model!!.profileUrl)
        holder.setName(model!!.name)
        holder.setAddress(model!!.linkbitAddress)
        onSelectListener?.let { holder.setOnClickListener { it(model.linkbitAddress)  }}
        //@TODO Selection mode
    }
}
