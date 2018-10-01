package com.linkbit.android.helper

import android.content.Context
import io.realm.Realm

val Context.realm: Realm
    get() = Realm.getDefaultInstance()