package com.linkbit.android.presentation.trasnaction.list

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.linkbit.android.R
import com.linkbit.android.entity.TransactionModel
import com.linkbit.android.presentation.BaseFragment
import com.linkbit.android.presentation.transaction.list.TransactionListAdpater
import kotlinx.android.synthetic.main.fragment_any_list.*

class TransactionListFragment : BaseFragment<TransactionListPresenter>(), TransactionListView {
    var address: String? = null
    lateinit var transactionListAdpater: TransactionListAdpater

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_any_list, container, false)
        this.transactionListAdpater = TransactionListAdpater(this.context)
        view.findViewById<RecyclerView>(R.id.recyclerview_any).adapter = transactionListAdpater
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
