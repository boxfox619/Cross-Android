package com.linkbit.android.presentation.wallet.manage.coinlist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.linkbit.android.R
import com.linkbit.android.entity.CoinModel
import rx.subjects.BehaviorSubject

class CoinListViewAdapter(
        private val mValues: ArrayList<CoinModel>,
        private val mListener: (item: CoinModel) -> Unit?,
        private val selectionMode: SelectionMode)
    : RecyclerView.Adapter<CoinListViewHolder>() {

    private val selectedIndexList: BehaviorSubject<ArrayList<CoinModel>> = BehaviorSubject.create()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinListViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_coin_item, parent, false)
        selectedIndexList.onNext(ArrayList())
        return CoinListViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinListViewHolder, position: Int) {
        val item = mValues[position]
        selectedIndexList.subscribe { holder.setSelected(it.contains(item)) }
        holder.setIcon(item.symbol)
        holder.setCoinText(item.symbol, item.name)
        holder.setIcon(item.symbol)
        holder.setCoinText(item.symbol, item.name)
        if (this.selectionMode == SelectionMode.MULTI) {
            holder.setVisibleCheckbox(true)
        } else if (this.selectionMode == SelectionMode.SINGLE) {
            holder.setVisibleRadioButton(true)
        }
        holder.setOnSelectListener(this.createSelectListener(item))
    }

    fun createSelectListener(item: CoinModel): (checked: Boolean, refresh: Boolean) -> Unit {
        return { checked, refersh ->
            selectedIndexList.first().subscribe{ list ->
                if (checked && refersh) {
                    list.clear()
                }
                if (checked) {
                    list.add(item)
                    mListener(item)
                } else {
                    list.remove(item)
                }
                selectedIndexList.onNext(list)
            }
        }
    }

    fun clear() {
        this.mValues.removeAll(this.mValues)
        notifyDataSetChanged()
    }

    fun addAll(items: List<CoinModel>) {
        this.mValues.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = mValues.size
}
