package com.linkbit.android.presentation.main.holder;

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.linkbit.android.R
import java.text.DecimalFormat

class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    constructor(context: Context, parent: ViewGroup) : this(LayoutInflater.from(context).inflate(R.layout.view_wallet_card, parent, false))

    fun setName(name : String){
        //itemView.findViewById<TextView>(R.id.tv_friend_name).text = name
    }

    fun setAddress(address: String){
        //itemView.findViewById<TextView>(R.id.tv_friend_address).text = address
    }
}
