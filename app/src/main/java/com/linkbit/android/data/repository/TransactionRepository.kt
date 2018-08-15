package com.linkbit.android.data.repository

import android.content.Context
import com.linkbit.android.data.model.transaction.TransactioNetworkEntityMapper
import com.linkbit.android.data.model.transaction.TransactionNetworkObject
import com.linkbit.android.data.network.Response
import com.linkbit.android.data.network.retrofit
import com.linkbit.android.domain.TransactionUsecase
import com.linkbit.android.entity.TransactionModel
import rx.Single

class TransactionRepository(private val context: Context) : TransactionUsecase {

    override fun getIntegralTransactionList(page: Int, count: Int): Single<List<TransactionModel>> {
        return Single.create { subscriber ->
            context.retrofit.transactionApi.getIntegralTransactionList(page, count).enqueue((object : Response<List<TransactionNetworkObject>>(context) {
                override fun setResponseData(code: Int, transactionList: List<TransactionNetworkObject>?) {
                    if (isSuccess(code) && transactionList != null) {
                        val transactionList: List<TransactionModel> = transactionList.map { it -> TransactioNetworkEntityMapper.fromNetworkObject(it) }
                        subscriber.onSuccess(transactionList)
                    } else {
                        subscriber.onError(null)
                    }
                }
            }))
        }
    }

    override fun getTransactionsByAddress(address: String, page: Int, count: Int): Single<List<TransactionModel>> {
        return Single.create { subscriber ->
            context.retrofit.transactionApi.getTransactionList(address, page, count).enqueue((object : Response<List<TransactionNetworkObject>>(context) {
                override fun setResponseData(code: Int, transactionList: List<TransactionNetworkObject>?) {
                    if (isSuccess(code) && transactionList != null) {
                        val transactionList: List<TransactionModel> = transactionList.map { it -> TransactioNetworkEntityMapper.fromNetworkObject(it) }
                        subscriber.onSuccess(transactionList)
                    } else {
                        subscriber.onError(null)
                    }
                }
            }))
        }
    }

    override fun getTransactionByHash(txHash: String): Single<TransactionModel> {
        return Single.create { subscriber ->
            context.retrofit.transactionApi.getTransaction(txHash).enqueue((object : Response<TransactionNetworkObject>(context) {
                override fun setResponseData(code: Int, transaction: TransactionNetworkObject?) {
                    if (isSuccess(code) && transaction != null) {
                        subscriber.onSuccess(TransactioNetworkEntityMapper.fromNetworkObject(transaction))
                    } else {
                        subscriber.onError(null)
                    }
                }
            }))
        }
    }

}