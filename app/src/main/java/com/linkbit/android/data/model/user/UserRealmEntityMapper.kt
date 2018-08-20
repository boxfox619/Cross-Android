package com.linkbit.android.data.model.coin

import com.linkbit.android.data.model.RealmEntityMapper
import com.linkbit.android.data.model.user.FriendRealmObject
import com.linkbit.android.data.model.user.UserRealmObject
import com.linkbit.android.entity.UserModel

object UserRealmEntityMapper : RealmEntityMapper<UserModel, UserRealmObject> {

    override fun fromRealmObject(obj: UserRealmObject): UserModel {
        return UserModel().apply {
            uid = obj.uid
            email = obj.email
            name = obj.name
            profileUrl = obj.profileUrl
            linkbitAddress = obj.linkbitAddress
        }
    }

    override fun toRealmObject(model: UserModel): UserRealmObject {
        return UserRealmObject().apply {
            uid = model.uid
            email = model.email
            name = model.name
            profileUrl = model.profileUrl
            linkbitAddress = model.linkbitAddress
        }
    }

    fun toFriendObject(model: UserModel): FriendRealmObject {
        return FriendRealmObject().apply {
            uid = model.uid
            email = model.email
            name = model.name
            profileUrl = model.profileUrl
            linkbitAddress = model.linkbitAddress
        }
    }
}