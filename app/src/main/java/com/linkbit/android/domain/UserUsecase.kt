package com.linkbit.android.domain

import com.linkbit.android.entity.UserModel
import io.reactivex.Observable
import io.reactivex.Single

interface UserUsecase : Repository {
    fun loadFriendList() : Single<List<UserModel>>
    fun getFriendList() : Observable<List<UserModel>>
    fun addFriend(uid: String) : Observable<Boolean>
    fun removeFriend(uid: String) : Observable<Boolean>
    fun searchUser(text: String): Observable<List<UserModel>>
}