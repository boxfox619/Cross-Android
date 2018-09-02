package com.linkbit.android.presentation.wallet.manage.coinlist

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.*
import com.bumptech.glide.Glide
import com.linkbit.android.helper.URLHelper
import kotlinx.android.synthetic.main.view_coin_item.view.*

class CoinListViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
    val iconView: ImageView = mView.iv_coin_icon
    val mContentView: TextView = mView.tv_coin_name
    val checkbox: CheckBox = mView.checkbox_coin_select
    val radioButton: RadioButton = mView.radio_coin_select

    override fun toString(): String {
        return super.toString() + " '" + mContentView.text + "'"
    }

    fun setSelected(state: Boolean){
        if(mContentView is LinearLayout){
            val view = mContentView.getChildAt(0)
            checkbox.isSelected = state
            radioButton.isSelected = state
        }
    }

    fun setIcon(symbol: String) {
        val url = URLHelper.createAssetUrl(itemView.context, symbol)
        Glide.with(mView).load(url).into(iconView)
    }

    fun setCoinText(symbol: String, name: String) {
        mContentView.text = String.format("%s - %s", symbol, name)
    }

    fun setVisibleCheckbox(visible: Boolean){
        if(visible){
            checkbox.visibility = View.VISIBLE
        }else{
            checkbox.visibility = View.INVISIBLE
        }
    }

    fun setVisibleRadioButton(visible: Boolean){
        if(visible){
            radioButton.visibility = View.VISIBLE
        }else{
            radioButton.visibility = View.INVISIBLE
        }
    }
}