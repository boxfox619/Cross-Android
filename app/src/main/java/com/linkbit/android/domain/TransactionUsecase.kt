package com.linkbit.android.domain

import com.linkbit.android.entity.TransactionModel
import rx.Single

interface TransactionUsecase : Repository {
    fun getIntegralTransactionList(page: Int, count: Int): Single<List<TransactionModel>>
    fun getTransactionsByAddress(address: String, page: Int, count: Int): Single<List<TransactionModel>>
    fun getTransactionByHash(txHash: String) : Single<TransactionModel>
}