package com.linkbit.android.ui.base

import android.text.Editable
import android.text.TextWatcher

open class SimpleTextChangeListener constructor(listener: (txt: String) -> Unit) : TextWatcher {
    val listener: (txt: String) -> Unit = listener

    override fun afterTextChanged(s: Editable?) {
        this.listener(s.toString())
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

}