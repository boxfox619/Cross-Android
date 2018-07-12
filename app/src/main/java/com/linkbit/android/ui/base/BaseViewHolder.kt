package com.linkbit.android.ui.base

import android.content.Context
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View

abstract class BaseViewHolder<ITEM>(open val adapter: RecyclerView.Adapter<*>, itemView: View) : RecyclerView.ViewHolder(itemView) {
    constructor(@LayoutRes layoutRes: Int,
                parent: ViewGroup?,
                adapter: RecyclerView.Adapter<*>)
            : this(adapter,
            LayoutInflater.from((adapter as? AbstractRecyclerAdapter<*, *>)?.context).inflate(layoutRes, parent, false))

    init {
        ButterKnife.bind(BaseRecyclerViewHolder@this, itemView)
    }

    abstract fun onViewHolder(item: ITEM?, position: Int)

    open protected val context: Context?
        get() = (adapter as? AbstractRecyclerAdapter<*, *>)?.context
}