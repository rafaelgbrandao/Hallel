package com.hallel.hallelsomevida

import androidx.lifecycle.LiveData
import com.hallel.core_ui.base.BaseViewModel
import com.hallel.core_ui.navigation.NavigationHelper.lvStartNavigationFromClick
import com.hallel.core_ui.navigation.NavigationHelper.lvStartNavigationFromFlow
import com.hallel.core_ui.navigation.NavigationObject

class MainViewModel: BaseViewModel() {

    fun startNavigationFromFlow(): LiveData<NavigationObject> = lvStartNavigationFromFlow

    fun startNavigationFromClick(): LiveData<Int> = lvStartNavigationFromClick
}


