package com.linkbit.android.domain

import com.linkbit.android.entity.UserModel
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface FriendUsecase : Usecase {
    fun loadFriendList() : Single<List<UserModel>>
    fun getFriendList() : Flowable<List<UserModel>>
    fun addFriend(uid: String) : Completable
    fun removeFriend(uid: String) : Completable
    fun searchUserByUid(uid:String) : Single<UserModel>
    fun searchUser(text: String, page: Int, count: Int): Single<List<UserModel>>
}