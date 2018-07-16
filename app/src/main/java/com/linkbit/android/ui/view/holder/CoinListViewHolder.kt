package com.linkbit.android.ui.view.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_coin.view.*

class CoinListViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val iconView: ImageView = mView.iv_coin_icon
        val mContentView: TextView = mView.tv_coin_name

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }