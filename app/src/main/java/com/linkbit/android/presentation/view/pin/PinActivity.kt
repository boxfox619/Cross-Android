package com.linkbit.android.presentation.view.pin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import com.github.ajalt.reprint.core.AuthenticationResult
import com.github.ajalt.reprint.rxjava2.RxReprint
import com.linkbit.android.R
import kotlinx.android.synthetic.main.activity_set_pin.*
import android.support.v4.content.ContextCompat
import android.widget.Toast
import com.github.ajalt.reprint.core.AuthenticationFailureReason
import com.linkbit.android.helper.ToastHelper


class PinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_pin)
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val useFingurePrint = prefs.getBoolean("fingurePrint", false)
        val correctPin = intent.getStringExtra("pin")
        val pinInputView = (pin_input_view as PinInputView)
        val resultIntent = Intent()
        pinInputView.asObservable().subscribe { pinNumbers ->
            if (correctPin != pinNumbers.toArray().joinToString("")) {
                pinInputView.setErrorMessage(getString(R.string.err_pin_not_match))
                pinInputView.reset()
            } else {
                showSuccess()
            }
        }
        setResult(Activity.RESULT_CANCELED, resultIntent)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M && useFingurePrint) {
            RxReprint.authenticate().subscribe { res ->
                when (res.status) {
                    AuthenticationResult.Status.SUCCESS -> showSuccess()
                    AuthenticationResult.Status.NONFATAL_FAILURE -> showError(res.failureReason, false, res.errorMessage, res.errorCode)
                    AuthenticationResult.Status.FATAL_FAILURE -> showError(res.failureReason, true, res.errorMessage, res.errorCode)
                }
            }
        }
    }

    private fun showSuccess() {
        val resultIntent = Intent()
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }

    private fun showError(failureReason: AuthenticationFailureReason, fatal: Boolean, errorMessage: CharSequence, errorCode: Int) {
        ToastHelper.showToast(this, errorMessage.toString())
        if (fatal) {
            //fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_fingerprint_white_24dp))
        }
    }
}