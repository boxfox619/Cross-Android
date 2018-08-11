package com.linkbit.android.data.model

interface RealmEntityMapper<Model, RealmObject> {
    fun fromRealmObject(obj: RealmObject): Model
    fun toRealmObject(model: Model): RealmObject
}