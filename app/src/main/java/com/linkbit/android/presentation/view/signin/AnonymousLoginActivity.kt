package com.linkbit.android.presentation.view.signin

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView

import com.linkbit.android.R
import com.linkbit.android.presentation.base.BaseActivity

import kotlinx.android.synthetic.main.activity_anonymous_login.*

class AnonymousLoginActivity : BaseActivity<AnonymousLoginPresenter>(), AnonymousLoginView {
    override val presenter: AnonymousLoginPresenter = AnonymousLoginPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anonymous_login)
        password.setOnEditorActionListener(TextView.OnEditorActionListener { _, id, _ ->
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                var emailStr = email.text.toString()
                val passwordStr = password.text.toString()
                this.presenter.attemptLogin(emailStr, passwordStr)
                return@OnEditorActionListener true
            }
            false
        })

        email_sign_in_button.setOnClickListener {
            var emailStr = email.text.toString()
            val passwordStr = password.text.toString()
            this.presenter.attemptLogin(emailStr, passwordStr)
        }
    }

    override fun setEmailInputError(error: String?) {
        email.error = error
        email.requestFocus()
    }

    override fun setPasswordInputError(error: String?) {
        password.error = error
        password.requestFocus()
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    override fun showProgress(show: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime).toLong()

            login_form.visibility = if (show) View.GONE else View.VISIBLE
            login_form.animate()
                    .setDuration(shortAnimTime)
                    .alpha((if (show) 0 else 1).toFloat())
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            login_form.visibility = if (show) View.GONE else View.VISIBLE
                        }
                    })

            login_progress.visibility = if (show) View.VISIBLE else View.GONE
            login_progress.animate()
                    .setDuration(shortAnimTime)
                    .alpha((if (show) 1 else 0).toFloat())
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            login_progress.visibility = if (show) View.VISIBLE else View.GONE
                        }
                    })
        } else {
            login_progress.visibility = if (show) View.VISIBLE else View.GONE
            login_form.visibility = if (show) View.GONE else View.VISIBLE
        }
    }
}
