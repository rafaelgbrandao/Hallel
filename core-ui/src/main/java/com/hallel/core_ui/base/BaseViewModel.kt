package com.hallel.core_ui.base

import androidx.lifecycle.ViewModel
import com.hallel.core.utils.Event
import com.hallel.core.utils.GenericError
import com.hallel.core_ui.helpers.lvShowExceptionError
import kotlinx.coroutines.*
import java.lang.Exception

open class BaseViewModel(
    private val dispatchers: Dispatchers = Dispatchers,
    private val job: Job = SupervisorJob()): ViewModel() {

    val coroutineScopeIO = CoroutineScope(dispatchers.IO + job)
    val coroutineScopeMain = CoroutineScope(dispatchers.Main + job)



    fun handleErrors(exception: Exception?) {
        lvShowExceptionError.postValue(Event(GenericError))
        /*when (exception) {
            is RuntimeException -> RuntimeException
            is Exception
            else -> GenericError
        }*/
    }

    override fun onCleared() {
        super.onCleared()
        job.cancelChildren()
    }
}