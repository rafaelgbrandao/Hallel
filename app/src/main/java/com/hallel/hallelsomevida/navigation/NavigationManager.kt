package com.hallel.hallelsomevida.navigation

import com.hallel.hallelsomevida.R
import com.hallel.splash.presentation.SplashFragment

object NavigationManager {

    fun getNavigationId(screenName: String): Int {
        return when (screenName) {
            SplashFragment::class.java.name -> R.id.action_splashFragment_to_accessFragment
            //AccessFragment::class.java.name ->
            else -> 0
        }
    }
}