package com.linkbit.android.service

import android.content.Context
import com.linkbit.android.model.Wallet
import com.linkbit.android.network.Connector
import com.linkbit.android.network.Response

class WalletService private constructor() {

    private object Holder { val INSTANCE = WalletService() }

    companion object {
        val instance: WalletService by lazy { Holder.INSTANCE }
    }

    lateinit var walletList: List<Wallet>

    fun getWalletList(ctx: Context, callback: (result: List<Wallet>) -> Unit) {
        if(walletList==null || walletList.isEmpty()){
            Connector(ctx).walletAPI.getWalletList().enqueue((object: Response<List<Wallet>>(ctx){
                override fun setResponseData(code: Int, walletList: List<Wallet>?) {
                    WalletService.instance.walletList = walletList!!
                    callback(walletList)
                }
            }))
        }else{
            callback(walletList)
        }
    }
}