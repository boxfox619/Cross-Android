package com.linkbit.android.presentation.view.pin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.linkbit.android.R
import kotlinx.android.synthetic.main.activity_set_pin.*


class SetPinActivity : AppCompatActivity() {
    private var pin: List<Int>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_pin)
        val pinInputView = (pin_input_view as PinInputView)
        pinInputView.asObservable().subscribe { pinNumbers ->
            if (pin == null) {
                pin = pinNumbers
                pinInputView.setMessage(getString(R.string.msg_request_password_confirm))
                pinInputView.reset()
            } else if (pin.toString() != pinNumbers.toArray().joinToString("")) {
                pin = null
                pinInputView.setErrorMessage(getString(R.string.err_pin_not_match))
                pinInputView.reset()
            } else {
                finish()
            }
        }
    }
}