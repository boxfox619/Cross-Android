package com.linkbit.android.service

import android.content.Context
import com.linkbit.android.model.TransactionStatus
import com.linkbit.android.model.Wallet
import com.linkbit.android.network.Connector
import com.linkbit.android.network.Response
import io.reactivex.Observable
import rx.subjects.BehaviorSubject

class WalletService private constructor() {

    private object Holder { val INSTANCE = WalletService() }

    companion object {
        val instance: WalletService by lazy { Holder.INSTANCE }
    }

    val walletList: BehaviorSubject<List<Wallet>> = BehaviorSubject.create()
    val transactionList: BehaviorSubject<List<TransactionStatus>> = BehaviorSubject.create()

    fun loadWalletList(ctx: Context) : Observable<List<Wallet>> {
        return Observable.create({
            val subscriber = it
            Connector(ctx).walletAPI.getWalletList().enqueue((object : Response<List<Wallet>>(ctx) {
                override fun setResponseData(code: Int, newWalletList: List<Wallet>?) {
                    walletList.onNext(newWalletList)
                    subscriber.onNext(newWalletList)
                    subscriber.onComplete()
                }
            }))
        })
    }

    fun loadTotalTransactionList(ctx: Context) : Observable<List<TransactionStatus>> {
        return Observable.create({
            val subscriber = it
            Connector(ctx).walletAPI.transactionList().enqueue((object : Response<List<TransactionStatus>>(ctx) {
                override fun setResponseData(code: Int, totalTransactionList: List<TransactionStatus>?) {
                    transactionList.onNext(totalTransactionList)
                    subscriber.onNext(totalTransactionList)
                    subscriber.onComplete()
                }
            }))
        })
    }
}