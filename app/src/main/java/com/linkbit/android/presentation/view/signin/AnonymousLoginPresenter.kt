package com.linkbit.android.presentation.view.signin

import android.app.Activity
import android.text.TextUtils
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.linkbit.android.R
import com.linkbit.android.data.repository.AuthRepository
import com.linkbit.android.helper.ToastHelper
import com.linkbit.android.presentation.base.Presenter

class AnonymousLoginPresenter(
        view: AnonymousLoginView,
        private val authRepository: AuthRepository = AuthRepository(view.getContext())
) : Presenter<AnonymousLoginView>(view) {

    fun attemptLogin(email: String, password: String) {
        view.setEmailInputError(null)
        view.setPasswordInputError(null)
        var cancel = false
        var copiedEmail = email

        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            view.setPasswordInputError(view.getContext().getString(R.string.error_invalid_password))
            cancel = true
        }

        if (TextUtils.isEmpty(email)) {
            view.setEmailInputError(view.getContext().getString(R.string.error_field_required))
            cancel = true
        } else if (!email.contains("@")) {
            copiedEmail += view.getContext().getString(R.string.signin_email_prefix)
        }

        if (!cancel) {
            firebaseSingin(copiedEmail, password)
        }
    }

    private fun firebaseSingin(email: String, password: String) {
        view.showProgress(true)
        this.authRepository.firebaseSignin(email, password).subscribe({
            view.finish(Activity.RESULT_OK)
        }, {
            view.showProgress(false)
            try {
                throw it
            } catch (e: FirebaseAuthInvalidCredentialsException) {
                view.setPasswordInputError(view.getContext().getString(R.string.err_fail_invalid_password))
                ToastHelper.showToast(view.getContext(), R.string.err_fail_invalid_password)
            } catch (e: Exception) {
                ToastHelper.showToast(view.getContext(), R.string.err_fail_anonymous_login)
            }
        })
    }

    private fun isEmailValid(email: String): Boolean {
        //TODO: Replace this with your own logic
        return email.contains("@")
    }

    private fun isPasswordValid(password: String): Boolean {
        //TODO: Replace this with your own logic
        return password.length > 4
    }
}