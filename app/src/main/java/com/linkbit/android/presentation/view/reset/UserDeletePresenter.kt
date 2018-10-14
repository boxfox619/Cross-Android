package com.linkbit.android.presentation.view.reset

import com.linkbit.android.data.repository.AuthRepository
import com.linkbit.android.helper.ToastHelper
import com.linkbit.android.presentation.base.Presenter

class UserDeletePresenter(
        view: UserDeleteView,
        private val authRepository: AuthRepository = AuthRepository(view.getContext())
) : Presenter<UserDeleteView>(view) {

    fun doReset() {
        this.view.showProgress(true)
        this.authRepository.unRegister().subscribe({
            this.view.showProgress(false)
            this.view.finish()
        },{
            ToastHelper.showToast(view.getContext(), "계정 삭제 실패")
            this.view.showProgress(false)
        })
    }
}