package com.linkbit.android.domain

import com.linkbit.android.entity.TransactionModel
import rx.Single

interface TransactionUsecase : Usecase {
    fun loadIntegralTransactionList(page: Int, count: Int): Single<List<TransactionModel>>
    fun loadTransactionsByAddress(address: String, page: Int, count: Int): Single<List<TransactionModel>>
    fun getTransactionByHash(txHash: String) : Single<TransactionModel>
}