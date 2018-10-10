package com.linkbit.android.presentation.view.signin

import com.linkbit.android.presentation.base.View

interface AnonymousLoginView : View {
    fun showProgress(status: Boolean)
    fun setEmailInputError(error: String?)
    fun setPasswordInputError(error: String?)
    fun finish(result: Int)

}