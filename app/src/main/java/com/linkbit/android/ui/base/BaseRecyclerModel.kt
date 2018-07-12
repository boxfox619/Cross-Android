package com.linkbit.android.ui.base;

interface BaseRecyclerModel<ITEM> {

        fun addItem(item: ITEM)

        fun addItem(position: Int, item: ITEM)

        fun addItems(items: List<ITEM>)

        fun clear()

        fun removeItem(item: ITEM)

        fun removeItem(position: Int)

        fun getItem(position: Int) : ITEM?

        fun getItems() : List<ITEM>
}
