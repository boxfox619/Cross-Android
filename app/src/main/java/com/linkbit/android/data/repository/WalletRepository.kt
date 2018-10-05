package com.linkbit.android.data.repository

import android.content.Context
import android.util.Log
import com.linkbit.android.data.model.coin.WalletNetworkEntityMapper
import com.linkbit.android.data.model.coin.WalletRealmEntityMapper
import com.linkbit.android.data.model.wallet.*
import com.linkbit.android.data.network.retrofit
import com.linkbit.android.domain.WalletUsecase
import com.linkbit.android.entity.WalletDataModel
import com.linkbit.android.entity.WalletModel
import com.linkbit.android.helper.realm
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.Observable

class WalletRepository(private val context: Context) : WalletUsecase {
    override fun loadWalletList(): Single<List<WalletModel>> {
        val single = context.retrofit.walletAPI.getWalletList()
                .map<List<WalletModel>> { it.map { WalletNetworkEntityMapper.fromNetworkObject(it) } }
        single.subscribe({
            context.realm.beginTransaction()
            context.realm.where(WalletRealmObject::class.java).findAll().deleteAllFromRealm()
            context.realm.copyToRealm(it.map { WalletRealmEntityMapper.toRealmObject(it) })
            context.realm.commitTransaction()
        }, {
            Log.d("Networking", "Fail the wallet list load")
            Log.d("Networking", it.message)
        })
        return single
    }

    override fun getWalletList(): Observable<List<WalletModel>> {
        return Observable.create { subscriber ->
            context.realm.where(WalletRealmObject::class.java).findAll().asObservable().subscribe {
                subscriber.onNext(it.map { WalletRealmEntityMapper.fromRealmObject(it) })
            }
        }
    }

    override fun createWallet(walletModel: WalletEditModel): Single<WalletModel> {
        return context.retrofit.walletAPI
                .createWallet(walletModel.coin.symbol, walletModel.name, walletModel.description, walletModel.password, walletModel.major, walletModel.open)
                .map<WalletModel> {
                    val walletrealmModel = WalletRealmEntityMapper.fromWalletCreated(it)
                    val walletModel = WalletRealmEntityMapper.fromRealmObject(walletrealmModel)
                    val walletRealmData = WalletDataRealmEntityMapper.fromWalletCreatedToRealm(it)
                    context.realm.beginTransaction()
                    context.realm.copyToRealm(walletRealmData)
                    context.realm.copyToRealm(walletrealmModel)
                    context.realm.commitTransaction()
                    walletModel
                }
    }

    override fun addWallet(walletModel: WalletEditModel, walletDataModel: WalletDataModel): Single<WalletModel> {
        return context.retrofit.walletAPI
                .addWallet(walletModel.coin.symbol, walletDataModel.accountAddress, walletModel.name, walletModel.description, walletModel.major, walletModel.open)
                .map<WalletModel> {
                    val realWalletModel = WalletNetworkEntityMapper.fromNetworkObject(it)
                    val walletRealmObject = WalletRealmEntityMapper.toRealmObject(realWalletModel)
                    val walletDataRealmObject = WalletDataRealmEntityMapper.toRealmObject(walletDataModel)
                    context.realm.beginTransaction()
                    context.realm.copyToRealm(walletRealmObject)
                    context.realm.copyToRealm(walletDataRealmObject)
                    context.realm.commitTransaction()
                    realWalletModel
                }
    }

    override fun loadWalletByAddress(address: String): Single<WalletModel> {
        return context.retrofit.walletAPI.getWalletInfo(address)
                .map<WalletModel> { WalletNetworkEntityMapper.fromNetworkObject(it) }
    }

    override fun getBalanceByAddress(address: String): Single<Double> {
        Log.d("Networking", "Try loading wallet list")
        return context.retrofit.walletAPI.getBalance(address).map { it.toDouble() }
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

    override fun updateWallet(address: String, name: String, description: String, major: Boolean, open: Boolean): Completable {
        return context.retrofit.walletAPI.updateWallet(address, name, description, major, open)
    }

    override fun createQRCode(address: String): Single<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}