package com.linkbit.android.presentation.view.pin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.linkbit.android.R


class SetPinActivity : AppCompatActivity() {
    private var pinInputView: PinInputView? = null

    private var pin: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_pin)

        pinInputView = findViewById(R.id.pin_input_view) as PinInputView
        pinInputView!!.asObservable().subscribe{ pinStr ->
                if (pin == null) {
                    pin = pinStr
                    pinInputView!!.setMessage(getString(R.string.msg_request_password_confirm))
                    pinInputView!!.reset()
                } else if (pin != pinStr) {
                    pin = null
                    pinInputView!!.setErrorMessage(getString(R.string.err_password_not_match))
                    pinInputView!!.reset()
                } else {
                    finish()
                }
            }
        }
    }
}