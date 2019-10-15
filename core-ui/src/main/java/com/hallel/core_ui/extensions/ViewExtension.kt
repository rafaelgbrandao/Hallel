package com.hallel.core_ui.extensions

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.google.android.material.textfield.TextInputEditText

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun TextInputEditText.getInputText(): String {
    return this.text.toString()
}