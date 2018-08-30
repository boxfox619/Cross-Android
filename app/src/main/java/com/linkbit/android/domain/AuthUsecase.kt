package com.linkbit.android.domain

import com.linkbit.android.entity.UserModel
import rx.Single

interface AuthUsecase : Usecase{
    fun login(token: String): Single<Boolean>
    fun logout(): Single<Boolean>
    fun loadAuthData(): Single<UserModel>
    fun getAuthData(): Single<UserModel>
}