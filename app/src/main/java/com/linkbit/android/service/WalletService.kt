package com.linkbit.android.service

import android.content.Context
import com.linkbit.android.entity.TransactionModel
import com.linkbit.android.entity.WalletModel
import com.linkbit.android.data.network.Connector
import com.linkbit.android.data.network.Response
import io.reactivex.Observable
import rx.subjects.BehaviorSubject

class WalletService private constructor() {

    private object Holder {
        val INSTANCE = WalletService()
    }

    companion object {
        val instance: WalletService by lazy { Holder.INSTANCE }
    }

    val walletModelList: BehaviorSubject<List<WalletModel>> = BehaviorSubject.create()
    val transactionListModel: BehaviorSubject<List<TransactionModel>> = BehaviorSubject.create()

    fun loadWalletList(ctx: Context): Observable<List<WalletModel>> {
        return Observable.create({
            val subscriber = it
            Connector(ctx).walletAPI.getWalletList().enqueue((object : Response<List<WalletModel>>(ctx) {
                override fun setResponseData(code: Int, newWalletModelList: List<WalletModel>?) {
                    if (isSuccess(code)) {
                        walletModelList.onNext(newWalletModelList)
                        subscriber.onNext(newWalletModelList)
                        subscriber.onComplete()
                    } else {
                        subscriber.onError(null)
                    }
                }
            }))
        })
    }

    fun loadTotalTransactionList(ctx: Context): Observable<List<TransactionModel>> {
        return Observable.create({
            val subscriber = it
            Connector(ctx).walletAPI.transactionList().enqueue((object : Response<List<TransactionModel>>(ctx) {
                override fun setResponseData(code: Int, totalTransactionListModel: List<TransactionModel>?) {
                    if (isSuccess(code)) {
                        transactionListModel.onNext(totalTransactionListModel)
                        subscriber.onNext(totalTransactionListModel)
                        subscriber.onComplete()
                    } else {
                        subscriber.onError(null)
                    }
                }
            }))
        })
    }
}