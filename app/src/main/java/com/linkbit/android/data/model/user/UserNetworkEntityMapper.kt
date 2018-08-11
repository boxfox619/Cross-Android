package com.linkbit.android.data.model.coin

import com.linkbit.android.data.model.NetworkEntityMapper
import com.linkbit.android.data.model.user.UserNetworkObject
import com.linkbit.android.entity.UserModel

object UserNetworkEntityMapper : NetworkEntityMapper<UserModel, UserNetworkObject>{

    override fun toNetworkObject(model: UserModel): UserNetworkObject {
        return UserNetworkObject().apply {
            uid = model.uid
            email = model.email
            name = model.name
            profileUrl = model.profileUrl
            linkbitAddress = model.linkbitAddress
        }
    }

    override fun fromNetworkObject(obj: UserNetworkObject): UserModel {
        return UserModel().apply {
            uid = obj.uid
            email = obj.email
            name = obj.name
            profileUrl = obj.profileUrl
            linkbitAddress = obj.linkbitAddress
        }
    }

}