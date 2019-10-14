package com.hallel.core_ui.navigation

import androidx.annotation.IdRes
import androidx.lifecycle.MutableLiveData

object NavigationHelper {

    val lvStartNavigationFromFlow = MutableLiveData<NavigationObject>()

    val lvStartNavigationFromClick = MutableLiveData<@IdRes Int>()
}