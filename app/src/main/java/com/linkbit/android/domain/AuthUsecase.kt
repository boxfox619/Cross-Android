package com.linkbit.android.domain

import com.linkbit.android.entity.UserModel
import io.reactivex.Completable
import io.reactivex.Single

interface AuthUsecase : Usecase{
    fun login(token: String): Completable
    fun logout(): Completable
    fun loadAuthData(): Single<UserModel>
    fun getAuthData(): Single<UserModel>
}