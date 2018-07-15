package com.linkbit.android.service

import android.content.Context
import com.linkbit.android.model.Wallet
import com.linkbit.android.network.Connector
import com.linkbit.android.network.Response
import rx.subjects.BehaviorSubject

class WalletService private constructor() {

    private object Holder { val INSTANCE = WalletService() }

    companion object {
        val instance: WalletService by lazy { Holder.INSTANCE }
    }

    val walletList: BehaviorSubject<List<Wallet>> = BehaviorSubject.create()

    fun loadWalletList(ctx: Context) {
        Connector(ctx).walletAPI.getWalletList().enqueue((object : Response<List<Wallet>>(ctx) {
            override fun setResponseData(code: Int, newWalletList: List<Wallet>?) {
                walletList.onNext(newWalletList)
            }
        }))
    }
}