package com.linkbit.android.domain

import com.linkbit.android.entity.WalletModel
import rx.Single
import rx.Observable

interface WalletUsecase : Usecase {
    fun loadWalletList() : Single<List<WalletModel>>
    fun getWalletList() : Observable<List<WalletModel>>
}