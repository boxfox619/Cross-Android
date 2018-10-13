package com.linkbit.android.presentation.view.pin

import android.content.Context
import android.graphics.PorterDuff
import android.widget.TextView
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import com.linkbit.android.R
import kotlinx.android.synthetic.main.view_pin_keyboard_button.view.*


class PinKeyboardButton : RelativeLayout {

    private var mContext: Context? = null

    constructor(context: Context, text: String, listener: View.OnClickListener) : super(context) {

        this.mContext = context
        initializeView(text, listener)
    }

    constructor(context: Context, drawable: Int, listener: View.OnClickListener) : super(context) {
        this.mContext = context
        initializeView(drawable, listener)
    }

    private fun initializeView(value: Any, listener: View.OnClickListener) {
        val inflater = mContext!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.view_pin_keyboard_button, this) as PinKeyboardButton
        pin_code_keyboard_button_ripple.setOnClickListener(listener)

        val textView = view.findViewById(R.id.keyboard_button_textview) as TextView
        if (value is String) {
            textView.text = value
        } else {
            textView.visibility = View.INVISIBLE
        }

        val imageView = view.findViewById(R.id.keyboard_button_imageview) as ImageView
        if (value is Int) {
            imageView.setImageDrawable(resources.getDrawable(value))
            imageView.setVisibility(View.VISIBLE)
            imageView.setColorFilter(resources.getColor(android.R.color.darker_gray), PorterDuff.Mode.SRC_IN)
        }

    }

}