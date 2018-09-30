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
                    } else {
                        subsrciber.onError(Throwable("Fail to create wallet"))
                    }
                }
            })
        }
    }

    override fun loadWalletByAddress(address: String): Single<WalletModel> {
        return Single.create { subscriber ->
            context.retrofit.walletAPI.getWalletInfo(address).enqueue(object : Response<WalletNetworkObject>(context) {
                override fun setResponseData(code: Int, wallet: WalletNetworkObject?) {
                    if (isSuccess(code) && wallet != null) {
                        subscriber.onSuccess(WalletNetworkEntityMapper.fromNetworkObject(wallet))
                    } else {
                        Log.d("Networking", "Fail the wallet load")
                        subscriber.onError(Throwable("Fail the wallet list load"))
                    }
                }
            })
        }
    }

    override fun getBalanceByAddress(address: String): Single<Double> {
        return Single.create { subscriber ->
            Log.d("Networking", "Try loading wallet list")
            context.retrofit.walletAPI.getBalance(address).enqueue(object : Response<String>(context) {
                override fun setResponseData(code: Int, balance: String?) {
                    if (isSuccess(code) && balance != null) {
                        subscriber.onSuccess(balance.toDouble())
                    } else {
                        subscriber.onError(Throwable("Get balance fail"))
                    }
                }
            })
        }
    }

    override fun getWalletByAddress(address: String): Single<WalletModel> {
        return Single.create { subsrciber ->
            val wallet = context.realm.where(WalletRealmObject::class.java).equalTo("accountAddress", address).or().equalTo("linkbitAddress", address).findFirst()
            if (wallet != null) {
                subsrciber.onSuccess(WalletRealmEntityMapper.fromRealmObject(wallet))
            } else {
                subsrciber.onError(Throwable("Wallet not found : by account address"))
            }
        }
    }


    override fun updateWallet(address: String, name: String, description: String, major: Boolean, open: Boolean): Single<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createQRCode(address: String): Single<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}