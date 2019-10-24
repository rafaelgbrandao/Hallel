package com.hallel.hallelsomevida.navigation

import android.util.Log
import com.hallel.core_ui.navigation.NavigationObject
import com.hallel.hallelsomevida.R
import com.hallel.home.presentation.HomeParticipantsAdapter
import com.hallel.localrepository.entity.Participant
import com.hallel.splash.presentation.SplashFragment

object NavigationManager {

    fun getNavigationId(navigationObject: NavigationObject): Int {
        return when (navigationObject.screenName) {
            SplashFragment::class.java.name -> handleSplashExtra(navigationObject.extras as Boolean)
            HomeParticipantsAdapter::class.java.name -> handleHomeAdapterExtra(navigationObject.extras as Participant)
            //AccessFragment::class.java.name ->
            else -> 0
        }
    }

    private fun handleHomeAdapterExtra(participant: Participant): Int {
        Log.v("Teste", participant.name)
        return 0
    }

    private fun handleSplashExtra(isUserLogged: Boolean): Int {
        return when {
            isUserLogged -> R.id.action_splashFragment_to_homeFragment
            else -> R.id.action_splashFragment_to_accessFragment
        }
    }
}