package com.hallel.core_ui.base

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hallel.core_ui.helpers.simpleAlertDialog

open class BaseActivity: AppCompatActivity() {

    fun setActionBarVisible(isVisible: Boolean) {
        when {
            isVisible -> supportActionBar?.show()
            else -> supportActionBar?.hide()
        }
    }

    fun showSimpleDialog(
        title: String?,
        message: String?,
        positiveButtonText: String?,
        positiveButtonListener: () -> Unit,
        negativeButtonText: String?,
        negativeButtonListener: () -> Unit,
        isCancelable: Boolean = true
    ) {
        simpleAlertDialog(
            context = this,
            title = title,
            message = message,
            positiveButtonText = positiveButtonText,
            positiveButtonListener = positiveButtonListener,
            negativeButtonText = negativeButtonText,
            negativeButtonListener = negativeButtonListener,
            isCancelable = isCancelable
        )
    }

    fun showToast(text: String, shortToast: Boolean = false){
        Toast.makeText(this, text, if(shortToast) Toast.LENGTH_SHORT else Toast.LENGTH_LONG).show()
    }
}