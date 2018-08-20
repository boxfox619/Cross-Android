package com.linkbit.android.data.repository

import android.content.Context
import com.linkbit.android.data.model.coin.CoinRealmEntityMapper
import com.linkbit.android.data.model.coin.CoinRealmObject
import com.linkbit.android.data.model.coin.WalletNetworkEntityMapper
import com.linkbit.android.data.model.coin.WalletRealmEntityMapper
import com.linkbit.android.data.model.wallet.WalletNetworkObject
import com.linkbit.android.data.model.wallet.WalletRealmObject
import com.linkbit.android.data.network.Response
import com.linkbit.android.data.network.retrofit
import com.linkbit.android.domain.WalletUsecase
import com.linkbit.android.entity.WalletModel
import com.linkbit.android.util.realm
import rx.Single
import rx.Observable

class WalletRepository(private val context: Context) : WalletUsecase {

    override fun loadWalletList(): Single<List<WalletModel>> {
        return Single.create { subscriber ->
            context.retrofit.walletAPI.getWalletList().enqueue(object : Response<List<WalletNetworkObject>>(context) {
                override fun setResponseData(code: Int, newWalletList: List<WalletNetworkObject>?) {
                    if (isSuccess(code) && newWalletList != null) {
                        val loadedWalletList: List<WalletModel> = newWalletList.map { it -> WalletNetworkEntityMapper.fromNetworkObject(it) }
                        context.realm.beginTransaction()
                        context.realm.where(WalletRealmObject::class.java).findAll().deleteAllFromRealm()
                        context.realm.copyToRealm(loadedWalletList.map { WalletRealmEntityMapper.toRealmObject(it) })
                        context.realm.commitTransaction()
                        subscriber.onSuccess(loadedWalletList)
                    } else {
                        subscriber.onError(null)
                    }
                }
            })
        }
    }

    override fun getWalletList(): Observable<List<WalletModel>> {
        return Observable.create{ subscriber ->
            context.realm.where(WalletRealmObject::class.java).findAll().asObservable().subscribe{
                subscriber.onNext(it.map{WalletRealmEntityMapper.fromRealmObject(it)})
            }
        }
    }
}