package com.linkbit.android.data.repository

import android.content.Context
import android.util.Log
import com.linkbit.android.data.model.coin.UserNetworkEntityMapper
import com.linkbit.android.data.model.coin.UserRealmEntityMapper
import com.linkbit.android.data.model.user.FriendRealmObject
import com.linkbit.android.data.model.user.UserNetworkObject
import com.linkbit.android.data.model.user.UserRealmObject
import com.linkbit.android.data.network.Response
import com.linkbit.android.data.network.retrofit
import com.linkbit.android.domain.FriendUsecase
import com.linkbit.android.entity.UserModel
import com.linkbit.android.helper.realm
import io.reactivex.BackpressureStrategy
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.disposables.Disposables
import io.realm.RealmResults



class FriendRepository(private val context: Context) : FriendUsecase {

    override fun loadFriendList(): Single<List<UserModel>> {
        Log.d("Networking", "Try loading friend list")
        val single = context.retrofit.friendAPI.friendList()
                .map<List<UserModel>> { it.map { UserNetworkEntityMapper.fromNetworkObject(it) } }
        single.subscribe({
            context.realm.beginTransaction()
            context.realm.where(FriendRealmObject::class.java).findAll().deleteAllFromRealm()
            context.realm.copyToRealm(it.map { UserRealmEntityMapper.toFriendObject(it) })
            context.realm.commitTransaction()
        }, {
            Log.d("Networking", "Fail the frield list load")
            Log.d("Networking", it.message)
        })
        return single
    }

    override fun getFriendList(): Flowable<List<UserModel>> {
        return Flowable.create({ emitter ->
            val results = context.realm.where(UserRealmObject::class.java).findAllAsync()
            val listener: ((result: RealmResults<UserRealmObject>) -> Unit) = {
                if (!emitter.isCancelled && results.isLoaded) {
                    emitter.onNext(results.map { UserRealmEntityMapper.fromRealmObject(it) })
                }
            }
            emitter.setDisposable(Disposables.fromRunnable {
                results.removeChangeListener(listener)
            })
            results.addChangeListener(listener)
        }, BackpressureStrategy.LATEST)

    }

    override fun addFriend(uid: String): Completable {
        return Completable.create{completable ->
            context.retrofit.friendAPI.addFriend(uid).subscribe({
                //@TODO Implement search user by uid
                searchUser(uid).subscribe({
                    if (it.isNotEmpty()) {
                        context.realm.beginTransaction()
                        context.realm.copyToRealm(UserRealmEntityMapper.toFriendObject(it.first()))
                        context.realm.commitTransaction()
                        completable.onComplete()
                    }else{
                        completable.onError(Throwable("User not found"))
                    }
                }, { completable.onError(it) })
            }, { completable.onError(it) })
        }
    }

    override fun removeFriend(uid: String): Completable {
        return Completable.create { completable ->
            context.retrofit.friendAPI.deleteFriend(uid).subscribe({
                context.realm.beginTransaction()
                context.realm.where(FriendRealmObject::class.java).equalTo("uid", uid).findFirst().deleteFromRealm()
                context.realm.commitTransaction()
                completable.onComplete()
            }, { completable.onError(it) })
        }
    }

    override fun searchUser(text: String): Single<List<UserModel>> {
        return context.retrofit.friendAPI.searchUsers(text).map<List<UserModel>> { it.map { UserNetworkEntityMapper.fromNetworkObject(it) } }
    }
}