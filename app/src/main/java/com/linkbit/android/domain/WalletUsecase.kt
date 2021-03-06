package com.linkbit.android.domain

import com.linkbit.android.data.model.wallet.WalletEditModel
import com.linkbit.android.entity.WalletDataModel
import com.linkbit.android.entity.WalletModel
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.Observable

interface WalletUsecase : Usecase {
    fun loadWalletList(): Single<List<WalletModel>>
    fun getWalletList(): Observable<List<WalletModel>>
    fun createWallet(walletModel: WalletEditModel): Single<WalletModel>
    fun addWallet(walletModel: WalletEditModel, walletDataModel: WalletDataModel): Single<WalletModel>
    fun loadWalletByAddress(address: String): Single<WalletModel>
    fun getWalletByAddress(address: String): Single<WalletModel>
    fun getBalanceByAddress(address: String): Single<Double>
    fun updateWallet(address: String, name: String, description: String, major: Boolean, open: Boolean): Completable

    fun createQRCode(address: String): Single<String>
}