package com.linkbit.android.ui.base;

interface BaseRecyclerModel<ITEM> {

    fun addItem(item: ITEM)

    fun addItem(position: Int, item: ITEM)

    fun addItems(items: List<ITEM>)

    fun clear()

    fun removeItem(item: ITEM)

    fun removeItem(position: Int)

    fun getItem(position: Int): ITEM?

    fun getItems(): List<ITEM>

    fun addSelectedItem(item: ITEM)

    fun addSelectedItem(position: Int, item: ITEM)

    fun addSelectedItems(items: List<ITEM>)

    fun clearSelectedItems()

    fun removeSelectedItem(item: ITEM)

    fun removeSelectedItem(position: Int)

    fun getSelectedItem(position: Int): ITEM?

    fun getSelectedItems(): List<ITEM>
}
