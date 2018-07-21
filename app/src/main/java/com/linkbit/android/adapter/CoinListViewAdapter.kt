package com.linkbit.android.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.linkbit.android.R
import com.linkbit.android.helper.URLHelper
import com.linkbit.android.model.Coin


import com.linkbit.android.ui.fragment.CoinListFragment.OnListFragmentInteractionListener
import com.linkbit.android.ui.view.holder.CoinListViewHolder
import java.security.AccessController.getContext

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
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
        val url = URLHelper.createAssetUrl(holder.itemView.context, item.symbol)
        Glide.with(holder.mView).load(url).into(holder.iconView)
        holder.mContentView.text = item.name

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size
}
