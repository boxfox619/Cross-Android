package com.linkbit.android.data.model

interface EntityMapper<Model, Object> {
    fun fromNetworkObject(obj :Object): Model
    fun toNetworkObject(model: Model): Object
}