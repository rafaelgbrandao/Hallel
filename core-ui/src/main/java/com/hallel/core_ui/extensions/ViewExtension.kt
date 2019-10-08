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

fun TextInputEditText.setTextChangeListener(onBefore: () -> Unit = {}, onAfter: () -> Unit = {}, onChange: () -> Unit = {}) {
    this.addTextChangedListener(
        object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) { onAfter() }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { onBefore() }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { onChange() }
        }
    )
}