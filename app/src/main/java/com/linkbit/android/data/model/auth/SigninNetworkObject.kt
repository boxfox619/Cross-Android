package com.linkbit.android.data.model.auth

import com.google.gson.annotations.SerializedName

class SigninNetworkObject {
    @SerializedName("result")
    var result: Boolean = false
    @SerializedName("token")
    lateinit var token: String
}