package com.linkbit.android.presentation.trasnaction.list

import android.os.Bundle
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
    override val presenter: TransactionListPresenter = TransactionListPresenter(this, address)
    lateinit var transactionListAdpater: TransactionListAdpater

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_any_list, container, false)
        this.transactionListAdpater = TransactionListAdpater(this.context)
        recyclerview_any.adapter = transactionListAdpater
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
