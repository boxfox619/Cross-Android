package com.linkbit.android.presentation.view.pin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.linkbit.android.R
import kotlinx.android.synthetic.main.activity_set_pin.*


class PinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_pin)
        val correctPin = intent.getStringExtra("pin")
        val pinInputView = (pin_input_view as PinInputView)
        pinInputView.asObservable().subscribe { pinNumbers ->
            if (correctPin == pinNumbers.toString()) {
                pinInputView.setErrorMessage(getString(R.string.err_pin_not_match))
                pinInputView.reset()
            } else {
                setResult(Activity.RESULT_OK)
                finish()
            }
        }
        setResult(Activity.RESULT_CANCELED)
    }
}