package com.linkbit.android.presentation.view.reset

import com.linkbit.android.presentation.base.View

interface UserDeleteView : View {

    fun showProgress(status: Boolean)
    fun finish()

}