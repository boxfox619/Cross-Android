package com.linkbit.android.domain

import com.linkbit.android.data.model.wallet.WalletEditModel
import com.linkbit.android.entity.WalletModel
import rx.Single
import rx.Observable

interface WalletUsecase : Usecase {
    fun loadWalletList(): Single<List<WalletModel>>
    fun getWalletList(): Observable<List<WalletModel>>
    fun createWallet(walletModel: WalletEditModel): Single<WalletModel>
    fun loadWalletByAddress(address: String): Single<WalletModel>
    fun getWalletByAddress(address: String): Single<WalletModel>
    fun getBalanceByAddress(address: String): Single<Double>
    fun updateWallet(address: String, name: String, description: String, major: Boolean, open: Boolean): Single<Boolean>

    fun createQRCode(address: String): Single<String>
}