package com.linkbit.android.data.repository

import android.content.Context
import com.linkbit.android.data.model.auth.SigninNetworkObject
import com.linkbit.android.data.network.Response
import com.linkbit.android.data.network.retrofit
import com.linkbit.android.domain.AuthUsecase
import rx.Single

class AuthRepository(private val context: Context) : AuthUsecase {

    override fun login(): Single<String> {
        return Single.create { subscriber ->
            context.retrofit.authAPI.signin().enqueue(object : Response<SigninNetworkObject>(context) {
                override fun setResponseData(code: Int, data: SigninNetworkObject?) {
                    if (isSuccess(code) && data != null && data.result) {
                        subscriber.onSuccess(data.token)
                    } else {
                        subscriber.onSuccess(null)
                    }
                }
            })
        }
    }

    override fun logout(): Single<Boolean> {
        return Single.create { subscriber ->
            context.retrofit.authAPI.logout().enqueue(object : Response<Void>(context) {
                override fun setResponseData(code: Int, void: Void?) {
                    if (isSuccess(code)) {
                        subscriber.onSuccess(true)
                    } else {
                        subscriber.onSuccess(false)
                    }
                }
            })
        }
    }
}