package com.linkbit.android.model.coin

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class CoinObject : RealmObject() {
    @PrimaryKey open lateinit var symbol : String
    open lateinit var name : String
}