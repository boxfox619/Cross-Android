package com.linkbit.android.service

import android.content.Context
import com.linkbit.android.entity.UserModel
import com.linkbit.android.data.network.Connector
import com.linkbit.android.data.network.Response
import io.reactivex.Observable
import rx.subjects.BehaviorSubject

class FriendService private constructor() {

    private object Holder {
        val INSTANCE = FriendService()
    }

    companion object {
        val instance: FriendService by lazy { Holder.INSTANCE }
    }

    val friendList: BehaviorSubject<List<UserModel>> = BehaviorSubject.create()

    fun loadFriendList(ctx: Context): Observable<List<UserModel>> {
        return Observable.create({
            val subscriber = it
            Connector(ctx).friendApi.friendList().enqueue((object : Response<List<UserModel>>(ctx) {
                override fun setResponseData(code: Int, loadedFriendList: List<UserModel>?) {
                    if (isSuccess(code)) {
                        friendList.onNext(loadedFriendList)
                        subscriber.onNext(loadedFriendList)
                        subscriber.onComplete()
                    } else {
                        subscriber.onError(null)
                    }
                }
            }))
        })
    }
}