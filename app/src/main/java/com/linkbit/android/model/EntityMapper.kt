package com.linkbit.android.model

interface EntityMapper<Model, Object> {
    fun fronRealmObject(obj :Object): Model
    fun toRealmObject(model: Model): Object
}