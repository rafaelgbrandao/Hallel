package com.hallel.core_ui.helpers

import androidx.annotation.IdRes
import androidx.lifecycle.MutableLiveData
import com.hallel.core.utils.Event
import com.hallel.core_ui.navigation.NavigationObject
import java.lang.Exception

val lvShowExceptionError = MutableLiveData<Event<Exception>>()

val lvStartNavigationFromDeepLink = MutableLiveData<Event<String>>()

val lvStartNavigationFromFlow = MutableLiveData<Event<NavigationObject>>()

val lvStartNavigationFromClick = MutableLiveData<Event<@IdRes Int>>()