package com.linkbit.android.domain

import com.linkbit.android.entity.WalletModel
import rx.Single
import rx.Observable

interface WalletUsecase : Usecase {
    fun loadWalletList() : Single<List<WalletModel>>
    fun getWalletList() : Observable<List<WalletModel>>
    fun createWallet(symbol: String, name: String, description: String, password: String, major: Boolean, open: Boolean) : Single<WalletModel>
    fun updateWallet(address: String, name: String, description: String, major: Boolean, open: Boolean): Single<Boolean>
}