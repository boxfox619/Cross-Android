package com.linkbit.android.presentation

import android.support.v7.widget.RecyclerView
import android.view.View

open abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun setOnClickListener(listener: () -> Unit):Unit
}