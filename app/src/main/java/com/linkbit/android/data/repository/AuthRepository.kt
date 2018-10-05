package com.linkbit.android.data.repository

import android.content.Context
import android.util.Log
import com.linkbit.android.data.model.coin.UserNetworkEntityMapper
import com.linkbit.android.data.model.coin.UserRealmEntityMapper
import com.linkbit.android.data.model.user.UserRealmObject
import com.linkbit.android.data.network.retrofit
import com.linkbit.android.domain.AuthUsecase
import com.linkbit.android.entity.UserModel
import com.linkbit.android.helper.realm
import io.reactivex.Completable
import io.reactivex.Single

class AuthRepository(private val context: Context) : AuthUsecase {

    override fun login(token: String): Completable {
        Log.d("Networking", "try singin")
        val completable = context.retrofit.authAPI.signin(token)
        completable.subscribe({
            Log.d("Networking", "signin success")
        }, {
            Log.d("Networking", "signin fail")
            Log.d("Networking", it.message)
        })
        return completable
    }

    override fun logout(): Completable {
        Log.d("Networking", "try signout")
        val completable = context.retrofit.authAPI.logout()
        completable.subscribe({
            Log.d("Networking", "logout success")
        }, {
            Log.d("Networking", "logout fail")
            Log.d("Networking", it.message)
        })
        return completable
    }
    override fun loadAuthData(): Single<UserModel> {
        return Single.create{ subscriber ->
            Log.d("Networking", "try getting auth info")
            val single = context.retrofit.authAPI.info()
            single.subscribe({
                val userModel = UserNetworkEntityMapper.fromNetworkObject(it)
                context.realm.beginTransaction()
                context.realm.where(UserRealmObject::class.java).findAll().deleteAllFromRealm()
                context.realm.copyToRealm(UserRealmEntityMapper.toRealmObject(userModel))
                context.realm.commitTransaction()
                subscriber.onSuccess(userModel)
            }, {
                Log.d("Networking", "Auth data load fail")
                Log.d("Networking", it.message)
                subscriber.onError(it)
            })
        }
    }

    override fun getAuthData(): Single<UserModel> {
        return Single.create { subscriber ->
            val userModel = context.realm.where(UserRealmObject::class.java).findFirst()
            if(userModel!=null){
                subscriber.onSuccess(UserRealmEntityMapper.fromRealmObject(userModel))
            }else{
                subscriber.onError(Throwable("Failt to get auth data"))
            }

        }
    }
}