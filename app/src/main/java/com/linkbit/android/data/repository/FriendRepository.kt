package com.linkbit.android.data.repository

import android.content.Context
import com.linkbit.android.data.model.coin.UserNetworkEntityMapper
import com.linkbit.android.data.model.coin.UserRealmEntityMapper
import com.linkbit.android.data.model.user.FriendRealmObject
import com.linkbit.android.data.model.user.UserNetworkObject
import com.linkbit.android.data.model.user.UserRealmObject
import com.linkbit.android.data.network.Response
import com.linkbit.android.data.network.retrofit
import com.linkbit.android.domain.FriendUsecase
import com.linkbit.android.entity.UserModel
import com.linkbit.android.util.realm
import rx.Observable
import rx.Single

class FriendRepository(private val context: Context) : FriendUsecase {

    override fun loadFriendList(): Single<List<UserModel>> {
        return Single.create { subscriber ->
            context.retrofit.friendAPI.friendList().enqueue(object : Response<List<UserNetworkObject>>(context) {
                override fun setResponseData(code: Int, friendList: List<UserNetworkObject>?) {
                    if (isSuccess(code) && friendList != null) {
                        context.realm.beginTransaction()
                        context.realm.where(FriendRealmObject::class.java).findAll().deleteAllFromRealm()
                        context.realm.copyToRealm(friendList.map { UserRealmEntityMapper.toFriendObject(UserNetworkEntityMapper.fromNetworkObject(it)) })
                        context.realm.commitTransaction()
                        subscriber.onSuccess(friendList.map { UserNetworkEntityMapper.fromNetworkObject(it) })
                    } else {
                        subscriber.onError(Throwable())
                    }
                }
            })
        }
    }

    override fun getFriendList(): Observable<List<UserModel>> {
        return Observable.create { obs ->
            context.realm.where(UserRealmObject::class.java).findAllAsync().asObservable().subscribe {
                obs.onNext(it.map { UserRealmEntityMapper.fromRealmObject(it) })
            }
        }
    }

    override fun addFriend(uid: String): Single<Boolean> {
        return Single.create{ subscriber ->
            context.retrofit.friendAPI.addFriend(uid).enqueue(object: Response<Void>(context){
                override fun setResponseData(code: Int, data: Void?) {
                    subscriber.onSuccess(isSuccess(code))
                }
            })
        }
    }

    override fun removeFriend(uid: String): Single<Boolean> {
        return Single.create{ subscriber ->
            context.retrofit.friendAPI.deleteFriend(uid).enqueue(object: Response<Void>(context){
                override fun setResponseData(code: Int, data: Void?) {
                    subscriber.onSuccess(isSuccess(code))
                }
            })
        }
    }

    override fun searchUser(text: String): Single<List<UserModel>> {
        return Single.create{ subscriber ->
            context.retrofit.friendAPI.searchUsers(text).enqueue(object: Response<List<UserNetworkObject>>(context){
                override fun setResponseData(code: Int, userList: List<UserNetworkObject>?) {
                    if (isSuccess(code) && userList != null) {
                        subscriber.onSuccess(userList.map { UserNetworkEntityMapper.fromNetworkObject(it) })
                    } else {
                        subscriber.onError(Throwable())
                    }
                }
            })
        }
    }
}