package com.hallel.core_ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hallel.core.utils.Event
import com.hallel.core.utils.GenericError
import com.hallel.core_ui.helpers.lvShowExceptionError
import kotlinx.coroutines.*
import java.lang.Exception

open class BaseViewModel(val dispatchers: Dispatchers = Dispatchers): ViewModel() {

    fun handleErrors(exception: Exception?) {
        lvShowExceptionError.postValue(Event(GenericError))
        /*when (exception) {
            is RuntimeException -> RuntimeException
            is Exception
            else -> GenericError
        }*/
    }
}
