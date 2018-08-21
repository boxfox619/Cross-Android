package com.linkbit.android.data.model.user

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class UserRealmObject : RealmObject() {
    @PrimaryKey
    @SerializedName("uid")
    lateinit var uid: String

    @SerializedName("email")
    lateinit var email: String

    @SerializedName("name")
    lateinit var name: String

    @SerializedName("profile")
    lateinit var profileUrl: String

    @SerializedName("linkbitAddress")
    lateinit var linkbitAddress: String
}