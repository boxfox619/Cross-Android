package com.linkbit.android.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.RadioButton
import com.linkbit.android.R
import com.linkbit.android.helper.SelectionMode
import com.linkbit.android.model.coin.CoinModel


import com.linkbit.android.presentation.wallet.manage.coinlist.CoinListViewHolder

class CoinListViewAdapter(
        private val mValues: List<CoinModel>,
        private val mListener: (item: CoinModel)->Unit?,
        private val selectionMode: SelectionMode)
    : RecyclerView.Adapter<CoinListViewHolder>() {

    private val selectedIndexList: ArrayList<Int> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinListViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_coin_item, parent, false)
        if(view is LinearLayout){
            if(selectionMode == SelectionMode.MULTI){
                view.addView(CheckBox(parent.context),0)
            }else if(selectionMode == SelectionMode.SINGLE){
                view.addView(RadioButton(parent.context),0)
            }
        }
        return CoinListViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinListViewHolder, position: Int) {
        val item = mValues[position]
        holder.setSelected(selectedIndexList.contains(position))
        holder.setIcon(item.symbol)
        holder.setCoinText(item.symbol, item.name)

        with(holder.mView) {
            tag = item
            setOnClickListener({
                if (selectedIndexList.contains(position)) {
                    selectedIndexList.remove(position)
                } else {
                    if (selectionMode == SelectionMode.SINGLE) {
                        selectedIndexList.forEach({
                            selectedIndexList.remove(it)
                            notifyItemChanged(it)
                        })
                    }
                    selectedIndexList.add(position)
                }
                mListener(item)
            })
        }
    }

    override fun getItemCount(): Int = mValues.size
}
