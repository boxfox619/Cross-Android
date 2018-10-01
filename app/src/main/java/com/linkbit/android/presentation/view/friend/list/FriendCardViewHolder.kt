package com.linkbit.android.presentation.view.friend.list;

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.linkbit.android.R
import com.linkbit.android.presentation.base.BaseViewHolder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_withdraw_step2.view.*

class FriendCardViewHolder(itemView: View) : BaseViewHolder(itemView) {
    constructor(context: Context, parent: ViewGroup) : this(LayoutInflater.from(context).inflate(R.layout.view_wallet_card, parent, false))

    fun setProfile(url: String){
        Picasso.get()
                .load(url)
                .resize(1600,1600)
                .onlyScaleDown()
                .into(itemView.findViewById<ImageView>(R.id.iv_friend_item_profile))
    }

    fun setName(name : String){
        itemView.findViewById<TextView>(R.id.tv_friend_item_name).text = name
    }

    fun setAddress(address: String){
        itemView.findViewById<TextView>(R.id.tv_friend_item_address).text = address
    }
    override fun setOnClickListener(listener: () -> Unit) {
        itemView.root_withdraw_step2.setOnClickListener{listener()}
    }

}
