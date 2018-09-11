package com.linkbit.android.domain

import com.linkbit.android.entity.TransactionModel
import rx.Single

interface WithdrawUsecase : Usecase {
    fun withdraw(symbol:String, accountAddress: String, password: String, targetAddress: String, amount: Double): Single<TransactionModel>
/*
    fun createQRCode(*//* @TODO QR 코드 생성 파라미터 정리 *//*): Single<String>
    fun createUrl(*//* @TODO URL 생성 파라미터 정리 *//*): Single<String>*/
}