package com.linkbit.android.data.model

interface NetworkEntityMapper<Model, NetworkObject> {
    fun fromNetworkObject(obj: NetworkObject): Model
    fun toNetworkObject(model: Model): NetworkObject
}