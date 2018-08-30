package com.linkbit.android.data.repository

import android.content.Context
import com.linkbit.android.data.model.coin.UserNetworkEntityMapper
import com.linkbit.android.data.model.coin.UserRealmEntityMapper
import com.linkbit.android.data.model.user.UserNetworkObject
import com.linkbit.android.data.model.user.UserRealmObject
import com.linkbit.android.data.network.Response
import com.linkbit.android.data.network.retrofit
import com.linkbit.android.domain.AuthUsecase
import com.linkbit.android.entity.UserModel
import com.linkbit.android.util.realm
import rx.Single

class AuthRepository(private val context: Context) : AuthUsecase {

    override fun login(token: String): Single<Boolean> {
        return Single.create { subscriber ->
            context.retrofit.authAPI.signin(token).enqueue(object : Response<Void>(context) {
                override fun setResponseData(code: Int, data: Void?) {
                    if (isSuccess(code)) {
                        subscriber.onSuccess(true)
                    } else {
                        subscriber.onSuccess(false)
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
    override fun loadAuthData(): Single<UserModel> {
        return Single.create { subscriber ->
            context.retrofit.authAPI.info().enqueue(object : Response<UserNetworkObject>(context) {
                override fun setResponseData(code: Int, data: UserNetworkObject?) {
                    if (isSuccess(code) && data !=null) {
                        val userModel = UserNetworkEntityMapper.fromNetworkObject(data)
                        context.realm.beginTransaction()
                        context.realm.where(UserRealmObject::class.java).findAll().deleteAllFromRealm()
                        context.realm.copyToRealm(UserRealmEntityMapper.toRealmObject(userModel))
                        context.realm.commitTransaction()
                        subscriber.onSuccess(userModel)
                    } else {
                        subscriber.onError(Throwable())
                    }
                }
            })
        }
    }

    override fun getAuthData(): Single<UserModel> {
        return Single.create { subscriber ->
            val userModel = context.realm.where(UserRealmObject::class.java).findFirst()
            if(userModel!=null){
                subscriber.onSuccess(UserRealmEntityMapper.fromRealmObject(userModel))
            }else{
                subscriber.onError(Throwable())
            }

        }
    }
}