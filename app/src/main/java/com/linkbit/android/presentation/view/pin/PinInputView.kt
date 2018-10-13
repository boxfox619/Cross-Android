package com.linkbit.android.presentation.view.pin

import android.content.Context
import android.graphics.Color
import android.widget.TextView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TableRow
import com.linkbit.android.R
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.layout_pin_number_input.view.*


class PinInputView @JvmOverloads constructor(private val mContext: Context, attrs: AttributeSet? = null) : RelativeLayout(mContext, attrs), View.OnClickListener {
    private val subject: BehaviorSubject<ArrayList<Int>> = BehaviorSubject.create()

    private var tvMessage: TextView? = null
    private var pinCheckLayout: LinearLayout? = null
    private var pinLength = 4
    private var pins: ArrayList<Int>? = null

    init {
        initializeView(attrs)
    }

    fun reset() {
        pins!!.removeAll(pins!!)
        for (i in 0 until pinCheckLayout!!.childCount) {
            (pinCheckLayout!!.getChildAt(i) as PinCheckCircle).setDeactive()
        }
    }

    fun setErrorMessage(msg: String) {
        tvMessage!!.text = msg
        tvMessage!!.setTextColor(Color.RED)
    }

    fun setMessage(msg: String) {
        tvMessage!!.text = msg
        tvMessage!!.setTextColor(Color.BLACK)
    }

    private fun initializeView(attrs: AttributeSet?) {
        pins = ArrayList()
        val inflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.layout_pin_number_input, this)
        tvMessage = view.findViewById(R.id.tv_message)
        pinCheckLayout = view.findViewById(R.id.layout_password_checker)

        if (attrs != null) {
            val a = mContext.obtainStyledAttributes(attrs, R.styleable.PinInputKeyboard)
            this.pinLength = a.getInt(R.styleable.PinInputKeyboard_pinLength, 0)
        }

        for (i in 0 until pinLength) {
            pinCheckLayout!!.addView(PinCheckCircle(mContext))
        }

        val tableLayout = view.layout_keyboard
        var row = TableRow(mContext)
        val lp = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT)
        row.setLayoutParams(lp)
        for (i in 1..9) {
            row.addView(PinKeyboardButton(mContext, i.toString() + "", this))
            if (i % 3 == 0) {
                tableLayout.addView(row, i / 3 - 1)
                row = TableRow(mContext)
                row.setLayoutParams(lp)
            }
        }
        row.addView(View(mContext))
        row.addView(PinKeyboardButton(mContext, "0", this))
        row.addView(PinKeyboardButton(mContext, R.drawable.ic_backspace_black, OnClickListener { onBacksapce() }))
        tableLayout.addView(row, 3)
    }

    private fun onBacksapce() {
        if (pins!!.size > 0) {
            (pinCheckLayout!!.getChildAt(pins!!.size - 1) as PinCheckCircle).setDeactive()
            pins!!.removeAt(pins!!.size - 1)
        }
    }

    override fun onClick(view: View) {
        if (pins!!.size < pinLength) {
            val pin = Integer.valueOf(((view.getParent() as View).findViewById(R.id.keyboard_button_textview) as TextView).text.toString())
            pins!!.add(pin)
            (pinCheckLayout!!.getChildAt(pins!!.size - 1) as PinCheckCircle).setActive()
            if (pins!!.size == pinLength) {
                    var pinStr = ""
                    for (pinItem in pins!!)
                        pinStr += pinItem
                    this.subject.onNext(pins)
                }
        }
    }

    fun asObservable(): Observable<ArrayList<Int>> {
        return this.subject
    }
}