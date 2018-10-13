package com.linkbit.android.presentation.view.pin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.linkbit.android.R


class PinCheckCircle(context: Context) : RelativeLayout(context) {
    private var circle: View? = null

    init {
        init()
    }

    private fun init() {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.view_pin_check_circle, this)

        circle = view.findViewById(R.id.view_circle)
        circle!!.setBackgroundResource(R.drawable.circle_dark)
    }

    fun setActive() {
        circle!!.setBackgroundResource(R.drawable.circle_active)
    }

    fun setDeactive() {
        circle!!.setBackgroundResource(R.drawable.circle_dark)
    }

}