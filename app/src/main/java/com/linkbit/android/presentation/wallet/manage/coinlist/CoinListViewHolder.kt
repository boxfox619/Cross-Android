package com.linkbit.android.presentation.wallet.manage.coinlist

import android.support.constraint.ConstraintLayout
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
    val root: ConstraintLayout = mView.view_coin_item_root
    init {
        root.setOnClickListener(createClickListener())
        for (i in 0 until root.childCount - 3 ) {
            root.getChildAt(i).setOnClickListener(createClickListener())
        }
    }

    fun createClickListener(): View.OnClickListener {
        return object : View.OnClickListener {
            override fun onClick(v: View?) {
                radioButton.isChecked = !radioButton.isChecked
                checkbox.isChecked = !checkbox.isChecked
            }
        }
    }

    override fun toString(): String {
        return super.toString() + " '" + mContentView.text + "'"
    }

    fun setSelected(state: Boolean) {
        checkbox.isChecked = state
        radioButton.isChecked = state
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
        } else {
            radioButton.visibility = View.INVISIBLE
        }
    }

    fun setOnSelectListener(listener: (checked: Boolean, referesh: Boolean) -> Unit) {
        this.radioButton.setOnCheckedChangeListener { v, c ->
            if (this.radioButton.visibility == View.VISIBLE) {
                listener(c, true)
            }
        }
        this.checkbox.setOnCheckedChangeListener { v, c ->
            if (checkbox.visibility == View.VISIBLE) {
                listener(c, false)
            }
        }
    }

}