package com.hallel.hallelsomevida

import androidx.lifecycle.LiveData
import com.hallel.core.utils.Event
import com.hallel.core_ui.base.BaseViewModel
import com.hallel.core_ui.helpers.lvShowExceptionError
import com.hallel.core_ui.helpers.lvStartNavigationFromClick
import com.hallel.core_ui.helpers.lvStartNavigationFromDeepLink
import com.hallel.core_ui.helpers.lvStartNavigationFromFlow
import com.hallel.core_ui.navigation.NavigationObject
import java.lang.Exception

class MainViewModel: BaseViewModel() {

    fun startNavigationFromFlow(): LiveData<Event<NavigationObject>> = lvStartNavigationFromFlow

    fun startNavigationFromClick(): LiveData<Event<Int>> = lvStartNavigationFromClick

    fun startNavigationFromDeepLink(): LiveData<Event<String>> = lvStartNavigationFromDeepLink

    fun showDialogErrorFromExceptions(): LiveData<Event<Exception>> = lvShowExceptionError
}


