package com.linkbit.android.presentation.trasnaction.list

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

import com.linkbit.android.R
import com.linkbit.android.entity.TransactionModel
import com.linkbit.android.presentation.EndlessRecyclerViewScrollListener
import com.linkbit.android.presentation.base.BaseFragment
import com.linkbit.android.presentation.view.transaction.list.TransactionListAdpater
import kotlinx.android.synthetic.main.fragment_any_list.view.*

class TransactionListFragment : BaseFragment<TransactionListPresenter>(), TransactionListView {
    var address: String? = null
    lateinit var transactionListAdpater: TransactionListAdpater

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_any_list, container, false)
        val layoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
        this.transactionListAdpater = TransactionListAdpater(this.context)
        view.recyclerview_any.adapter = transactionListAdpater
        view.recyclerview_any.layoutManager = layoutManager
        view.recyclerview_any.addOnScrollListener(object : EndlessRecyclerViewScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                presenter.loadTransaction(page)
            }
        })
        this.presenter = TransactionListPresenter(this, address)
        this.presenter.loadTransaction()
        return view
    }

    override fun onDetach() {
        super.onDetach()
        presenter.destory()
    }

    override fun addTransationItems(items: List<TransactionModel>) {
        transactionListAdpater.addItems(items)
        transactionListAdpater.notifyDataSetChanged()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                TransactionListFragment()

        fun newInstance(address: String) =
                TransactionListFragment().apply {
                    this.address = address
                }
    }
}
