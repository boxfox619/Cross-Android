package com.linkbit.android.domain

import com.linkbit.android.entity.UserModel
import rx.Single
import rx.Observable

interface FriendUsecase : Usecase {
    fun loadFriendList() : Single<List<UserModel>>
    fun getFriendList() : Observable<List<UserModel>>
    fun addFriend(uid: String) : Single<Boolean>
    fun removeFriend(uid: String) : Single<Boolean>
    fun searchUser(text: String): Single<List<UserModel>>
}