package com.linkbit.android.presentation.adapter.coin

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import com.linkbit.android.R
import com.linkbit.android.helper.URLHelper
import kotlinx.android.synthetic.main.view_coin_item.view.*

class CoinListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    constructor(context: Context, parent: ViewGroup) : this(LayoutInflater.from(context).inflate(R.layout.view_coin_item, parent, false))

    init {
        val root: ViewGroup = itemView.view_coin_item_root
        root.setOnClickListener(createClickListener())
        for (i in 0 until root.childCount - 3 ) {
            root.getChildAt(i).setOnClickListener(createClickListener())
        }
    }

    fun createClickListener(): View.OnClickListener {
        return object : View.OnClickListener {
            override fun onClick(v: View?) {
                itemView.radio_coin_select.isChecked = !itemView.radio_coin_select.isChecked
                itemView.checkbox_coin_select.isChecked = !itemView.checkbox_coin_select.isChecked
            }
        }
    }

    override fun toString(): String {
        return super.toString() + " '" + itemView.tv_coin_name.text + "'"
    }

    fun setSelected(state: Boolean) {
        itemView.checkbox_coin_select.isChecked = state
        itemView.radio_coin_select.isChecked = state
    }

    fun setIcon(symbol: String) {
        val url = URLHelper.createAssetUrl(itemView.context, symbol)
        Glide.with(itemView).load(url).into(itemView.iv_coin_icon)
    }

    fun setCoinText(symbol: String, name: String) {
        itemView.tv_coin_name.text = String.format("%s - %s", symbol, name)
    }

    fun setVisibleCheckbox(visible: Boolean){
        if(visible){
            itemView.checkbox_coin_select.visibility = View.VISIBLE
        }else{
            itemView.checkbox_coin_select.visibility = View.INVISIBLE
        }
    }

    fun setVisibleRadioButton(visible: Boolean){
        if(visible){
            itemView.radio_coin_select.visibility = View.VISIBLE
        } else {
            itemView.radio_coin_select.visibility = View.INVISIBLE
        }
    }

    fun setOnSelectListener(listener: (checked: Boolean, referesh: Boolean) -> Unit) {
        this.itemView.radio_coin_select.setOnCheckedChangeListener { v, c ->
            if (this.itemView.radio_coin_select.visibility == View.VISIBLE) {
                listener(c, true)
            }
        }
        this.itemView.checkbox_coin_select.setOnCheckedChangeListener { v, c ->
            if (itemView.checkbox_coin_select.visibility == View.VISIBLE) {
                listener(c, false)
            }
        }
    }

}