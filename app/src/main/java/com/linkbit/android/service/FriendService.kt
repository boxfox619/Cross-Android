package com.linkbit.android.service

import android.content.Context
import com.linkbit.android.model.User
import com.linkbit.android.network.Connector
import com.linkbit.android.network.Response
import io.reactivex.Observable
import rx.subjects.BehaviorSubject

class FriendService private constructor() {

    private object Holder { val INSTANCE = FriendService() }

    companion object {
        val instance: FriendService by lazy { Holder.INSTANCE }
    }

    val friendList: BehaviorSubject<List<User>> = BehaviorSubject.create()

    fun loadFriendList(ctx: Context) : Observable<List<User>> {
        return Observable.create({
            val subscriber = it
            Connector(ctx).friendApi.friendList().enqueue((object : Response<List<User>>(ctx) {
                override fun setResponseData(code: Int, loadedFriendList: List<User>?) {
                    friendList.onNext(loadedFriendList)
                    subscriber.onNext(loadedFriendList)
                    subscriber.onComplete()
                }
            }))
        })
    }
}