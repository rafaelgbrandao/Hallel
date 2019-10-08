package com.hallel.core_ui.helpers

import android.content.Context
import androidx.appcompat.app.AlertDialog

fun simpleAlertDialog(
    context: Context,
    title: String? = null,
    message: String? = null,
    positiveButtonText: String? = null,
    positiveButtonListener: () -> Unit = {},
    negativeButtonText: String? = null,
    negativeButtonListener: () -> Unit = {},
    isCancelable: Boolean = true
) {
    AlertDialog.Builder(context).apply {
        title?.let { setTitle(title) }
        message?.let{ setMessage(message)}
        positiveButtonText?.let { btnText ->
            setPositiveButton(btnText) { _, _ -> positiveButtonListener() }
        }
        negativeButtonText?.let { btnText ->
            setNegativeButton(btnText) { _, _ -> negativeButtonListener() }
        }
        setCancelable(isCancelable)
    }.show()
}