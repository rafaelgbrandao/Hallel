package com.hallel.core_ui.navigation

import androidx.annotation.IdRes
import androidx.lifecycle.MutableLiveData
import com.hallel.core.utils.Event

object NavigationHelper {

    val lvStartNavigationFromDeepLink = MutableLiveData<Event<String>>()

    val lvStartNavigationFromFlow = MutableLiveData<Event<NavigationObject>>()

    val lvStartNavigationFromClick = MutableLiveData<Event<@IdRes Int>>()
}