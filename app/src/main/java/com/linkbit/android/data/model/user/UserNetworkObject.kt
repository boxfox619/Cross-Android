package com.linkbit.android.data.model.user

import com.google.gson.annotations.SerializedName

class UserNetworkObject {
    @SerializedName("uid")
    lateinit var uid: String

    @SerializedName("email")
    lateinit var email: String

    @SerializedName("name")
    lateinit var name: String

    @SerializedName("profileUrl")
    lateinit var profileUrl: String

    @SerializedName("linkbitAddress")
    lateinit var linkbitAddress: String
}