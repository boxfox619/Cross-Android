package com.linkbit.android.domain

import com.linkbit.android.entity.WalletModel
import io.reactivex.Observable
import io.reactivex.Single

interface WalletUsecase : Repository {
    fun loadAllOwnWalletList() : Single<List<WalletModel>>
    fun getWalletList() : Observable<List<WalletModel>>
}