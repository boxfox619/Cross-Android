package com.linkbit.android.presentation.adapter.coin

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.linkbit.android.R
import com.linkbit.android.entity.CoinModel
import com.linkbit.android.presentation.adapter.AbstractRecyclerAdapter
import com.linkbit.android.presentation.adapter.SelectionMode
import rx.subjects.BehaviorSubject

class CoinListViewAdapter(
        context: Context,
        private val mListener: (item: CoinModel?) -> Unit?,
        private val selectionMode: SelectionMode)
    : AbstractRecyclerAdapter<CoinModel, CoinListViewHolder>(context) {
    override fun onItemViewType(position: Int): Int = 0
    private val selectedIndexList: BehaviorSubject<ArrayList<CoinModel>> = BehaviorSubject.create()

    init {
        selectedIndexList.subscribe { list ->
            if (list.size > 0) {
                mListener(list.get(0))
            } else {
                mListener(null)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinListViewHolder {
        val view = CoinListViewHolder(parent.context, parent)
        selectedIndexList.onNext(ArrayList())
        return view
    }

    override fun onBindViewHolder(holder: CoinListViewHolder, position: Int) {
        val item = getItem(position)
        item!!.let { item ->
            selectedIndexList.subscribe { holder.setSelected(it.contains(item)) }
            holder.setIcon(item.symbol)
            holder.setCoinText(item.symbol, item.name)
            if (this.selectionMode == SelectionMode.MULTI) {
                holder.setVisibleCheckbox(true)
            } else if (this.selectionMode == SelectionMode.SINGLE) {
                holder.setVisibleRadioButton(true)
            }
            holder.setOnSelectListener(this.createSelectListener(item))
        }
    }

    fun createSelectListener(item: CoinModel): (checked: Boolean, refresh: Boolean) -> Unit {
        return { checked, refersh ->
            selectedIndexList.first().subscribe { list ->
                if (checked && refersh) {
                    list.clear()
                }
                if (checked) {
                    list.add(item)
                } else {
                    list.remove(item)
                }
                selectedIndexList.onNext(list)
            }
        }
    }
}
