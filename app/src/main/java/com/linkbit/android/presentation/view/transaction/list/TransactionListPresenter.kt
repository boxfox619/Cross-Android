package com.linkbit.android.presentation.trasnaction.list

import android.util.Log
import com.linkbit.android.R
import com.linkbit.android.data.repository.TransactionRepository
import com.linkbit.android.helper.ToastHelper
import com.linkbit.android.presentation.base.Presenter

class TransactionListPresenter(
        view: TransactionListView,
        private val address: String?,
        private val transactionRepository: TransactionRepository = TransactionRepository(view.getContext())
) : Presenter<TransactionListView>(view) {
    fun loadTransaction(page: Int = 0, count: Int = 10) {
        if (address == null) {
            transactionRepository.loadIntegralTransactionList(page, count).subscribe ({
                view.addTransationItems(it)
            },{
                ToastHelper.showToast(view.getContext(), R.string.msg_fail_load_transaction)
                Log.d(this.javaClass.`package`.name, it.message)
            })
        } else {
            transactionRepository.loadTransactionsByAddress(address, page, count).subscribe ({
                view.addTransationItems(it)
            },{
                ToastHelper.showToast(view.getContext(), R.string.msg_fail_load_transaction)
                Log.d(this.javaClass.`package`.name, it.message)
            })
        }
    }
}