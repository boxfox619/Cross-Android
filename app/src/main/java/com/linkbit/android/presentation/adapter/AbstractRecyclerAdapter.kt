package com.linkbit.android.presentation.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView

abstract class AbstractRecyclerAdapter<ITEM, VIEW_TYPE : RecyclerView.ViewHolder?>(open val context: Context) : RecyclerView.Adapter<VIEW_TYPE>(), BaseRecyclerModel<ITEM> {
    private val selectedList: MutableList<ITEM> = ArrayList()
    private val itemList: MutableList<ITEM> = ArrayList()

    abstract fun onItemViewType(position: Int): Int

    override fun getItemViewType(position: Int)
            = onItemViewType(position)

    override fun getItemCount()
            = itemList.size

    override fun addItem(item: ITEM) {
        itemList.add(item)
    }

    override fun addItem(position: Int, item: ITEM) {
        itemList.add(position, item)
    }

    override fun addItems(items: List<ITEM>) {
        itemList.addAll(items)
    }

    override fun clear() {
        itemList.clear()
    }

    override fun removeItem(item: ITEM) {
        itemList.remove(item)
    }

    override fun removeItem(position: Int) {
        itemList.removeAt(position)
    }
    override fun getItem(position: Int)
            = itemList.getOrNull(position)

    override fun getItems() = itemList


    override fun addSelectedItem(item: ITEM) {
        selectedList.add(item)
    }

    override fun addSelectedItem(position: Int, item: ITEM) {
        selectedList.add(position, item)
    }

    override fun addSelectedItems(items: List<ITEM>) {
        selectedList.addAll(items)
    }

    override fun clearSelectedItems() {
        selectedList.clear()
    }

    override fun removeSelectedItem(item: ITEM) {
        selectedList.remove(item)
    }

    override fun removeSelectedItem(position: Int) {
        selectedList.removeAt(position)
    }
    override fun getSelectedItem(position: Int)
            = selectedList.getOrNull(position)

    override fun getSelectedItems() = selectedList
}