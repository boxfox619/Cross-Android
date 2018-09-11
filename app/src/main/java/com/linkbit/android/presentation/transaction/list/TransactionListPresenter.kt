package com.linkbit.android.presentation.trasnaction.list

import android.util.Log
import com.linkbit.android.R
import com.linkbit.android.data.repository.TransactionRepository
import com.linkbit.android.helper.ToastHelper
import com.linkbit.android.presentation.Presenter

class TransactionListPresenter(
        view: TransactionListView,
        private val address: String?,
        private val transactionRepository: TransactionRepository = TransactionRepository(view.getContext())
) : Presenter<TransactionListView>(view) {
    private var page: Int = 0
    private val count: Int = 10

    fun loadTransaction() {
        if (address == null) {
            transactionRepository.getIntegralTransactionList(page, count).subscribe ({
                page += 1
                view.addTransationItems(it)
            },{
                ToastHelper.showToast(view.getContext(), R.string.msg_fail_load_transaction)
                Log.d(this.javaClass.`package`.name, it.message)
            })
        } else {
            transactionRepository.getTransactionsByAddress(address, page, count).subscribe {
                page += 1
                view.addTransationItems(it)
            }
        }
    }
}