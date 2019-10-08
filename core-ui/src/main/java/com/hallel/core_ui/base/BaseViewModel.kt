package com.hallel.core_ui.base

import androidx.annotation.IdRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BaseViewModel: ViewModel() {

    val lvStartNavigationFromFlow = MutableLiveData<Pair<String, Any>>()

    val lvStartNavigationFromClick = MutableLiveData<@IdRes Int>()

}