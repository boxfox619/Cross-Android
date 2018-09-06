package com.linkbit.android.data.repository

import android.content.Context
import android.util.Log
import com.linkbit.android.data.model.coin.CoinRealmEntityMapper
import com.linkbit.android.data.model.coin.CoinRealmObject
import com.linkbit.android.data.model.coin.WalletNetworkEntityMapper
import com.linkbit.android.data.model.coin.WalletRealmEntityMapper
import com.linkbit.android.data.model.wallet.*
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
            Log.d("Networking", "Try loading wallet list")
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
                        Log.d("Networking", "Fail the wallet list load")
                        subscriber.onError(Throwable("Fail the wallet list load"))
                    }
                }
            })
        }
    }

    override fun getWalletList(): Observable<List<WalletModel>> {
        return Observable.create { subscriber ->
            context.realm.where(WalletRealmObject::class.java).findAll().asObservable().subscribe {
                subscriber.onNext(it.map { WalletRealmEntityMapper.fromRealmObject(it) })
            }
        }
    }

    override fun createWallet(walletModel: WalletEditModel): Single<WalletModel> {
        return Single.create { subsrciber ->
            context.retrofit.walletAPI.createWallet(walletModel.coin.symbol, walletModel.name, walletModel.description, walletModel.password, walletModel.major, walletModel.open).enqueue(object : Response<WalletCreateNetworkObject>(context) {
                override fun setResponseData(code: Int, walletCreatedResult: WalletCreateNetworkObject?) {
                    if (isSuccess(code) && walletCreatedResult != null) {
                        val walletrealmModel = WalletRealmEntityMapper.fromWalletCreated(walletCreatedResult)
                        val walletModel = WalletRealmEntityMapper.fromRealmObject(walletrealmModel)
                        val walletRealmData = WalletDataRealmEntityMapper.fromWalletCreatedToRealm(walletCreatedResult)
                        context.realm.beginTransaction()
                        context.realm.copyToRealm(walletRealmData)
                        context.realm.copyToRealm(walletrealmModel)
                        context.realm.commitTransaction()
                        subsrciber.onSuccess(walletModel)
                    }else{
                        subsrciber.onError(Throwable("Fail to create wallet"))
                    }
                }
            })
        }
    }

    override fun updateWallet(address: String, name: String, description: String, major: Boolean, open: Boolean): Single<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}