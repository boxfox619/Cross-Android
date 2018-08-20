package com.linkbit.android.domain

import rx.Single

interface AuthUsecase : Usecase{
    fun login(token: String): Single<Boolean>
    fun logout(): Single<Boolean>
}