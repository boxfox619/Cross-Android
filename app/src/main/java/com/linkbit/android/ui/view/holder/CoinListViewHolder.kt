package com.linkbit.android.ui.view.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.linkbit.android.helper.URLHelper
import kotlinx.android.synthetic.main.fragment_coin.view.*

class CoinListViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
    val iconView: ImageView = mView.iv_coin_icon
    val mContentView: TextView = mView.tv_coin_name

    override fun toString(): String {
        return super.toString() + " '" + mContentView.text + "'"
    }

    fun setIcon(symbol: String) {
        val url = URLHelper.createAssetUrl(itemView.context, symbol)
        Glide.with(mView).load(url).into(iconView)
    }

    fun setCoinText(symbol: String, name: String) {
        mContentView.text = String.format("%s - %s", symbol, name)
    }
}