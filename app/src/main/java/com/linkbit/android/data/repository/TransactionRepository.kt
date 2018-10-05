package com.linkbit.android.data.repository

import android.content.Context
import com.linkbit.android.data.model.transaction.TransactioNetworkEntityMapper
import com.linkbit.android.data.network.retrofit
import com.linkbit.android.domain.TransactionUsecase
import com.linkbit.android.entity.TransactionModel
import io.reactivex.Single

class TransactionRepository(private val context: Context) : TransactionUsecase {

    override fun loadIntegralTransactionList(page: Int, count: Int): Single<List<TransactionModel>> {
        return context.retrofit.transactionAPI.getIntegralTransactionList(page, count)
                .map<List<TransactionModel>> { it.map { TransactioNetworkEntityMapper.fromNetworkObject(it) } }
    }

    override fun loadTransactionsByAddress(address: String, page: Int, count: Int): Single<List<TransactionModel>> {
        return context.retrofit.transactionAPI.getTransactionList(address, page, count)
                .map<List<TransactionModel>> { it.map { it -> TransactioNetworkEntityMapper.fromNetworkObject(it) } }
    }

    override fun getTransactionByHash(txHash: String): Single<TransactionModel> {
        return context.retrofit.transactionAPI.getTransaction(txHash).map<TransactionModel> { TransactioNetworkEntityMapper.fromNetworkObject(it) }
    }

}