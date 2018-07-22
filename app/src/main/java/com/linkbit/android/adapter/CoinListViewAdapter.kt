package com.linkbit.android.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.linkbit.android.R
import com.linkbit.android.model.Coin


import com.linkbit.android.ui.view.holder.CoinListViewHolder

class CoinListViewAdapter(
        private val mValues: List<Coin>,
        private val mListener: (item: Coin)->Unit?)
    : RecyclerView.Adapter<CoinListViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Coin
            mListener(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinListViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_coin, parent, false)
        return CoinListViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinListViewHolder, position: Int) {
        val item = mValues[position]
        holder.setIcon(item.symbol)
        holder.setCoinText(item.symbol, item.name)

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size
}
