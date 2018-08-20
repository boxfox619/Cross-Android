package com.linkbit.android.domain

import rx.Single

interface AuthUsecase : Repository{
    fun login(): Single<String>
    fun logout(): Single<Boolean>
}