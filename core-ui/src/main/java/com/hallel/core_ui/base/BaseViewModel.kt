package com.hallel.core_ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

open class BaseViewModel(
    private val dispatchers: Dispatchers = Dispatchers,
    private val job: Job = SupervisorJob()): ViewModel() {

    val coroutineScopeIO = CoroutineScope(dispatchers.IO + job)
    val coroutineScopeMain = CoroutineScope(dispatchers.Main + job)

    override fun onCleared() {
        super.onCleared()
        job.cancelChildren()
    }
}