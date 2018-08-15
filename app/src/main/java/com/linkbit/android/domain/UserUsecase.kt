package com.linkbit.android.domain

import com.linkbit.android.entity.UserModel
import rx.Single
import rx.Observable

interface UserUsecase : Repository {
    fun loadFriendList() : Single<List<UserModel>>
    fun getFriendList() : Observable<List<UserModel>>
    fun addFriend(uid: String) : Observable<Boolean>
    fun removeFriend(uid: String) : Observable<Boolean>
    fun searchUser(text: String): Observable<List<UserModel>>
}