package com.hallel.splash.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hallel.core.utils.Event
import com.hallel.core_ui.base.BaseViewModel
import com.hallel.core_ui.helpers.lvStartNavigationFromFlow
import com.hallel.core_ui.navigation.NavigationObject
import com.hallel.splash.BuildConfig
import com.hallel.splash.repository.SplashRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashViewModel(private val splashRepository: SplashRepository): BaseViewModel() {

    fun showAppUpdateDialog(): LiveData<Unit> = lvLastVersionNumber
    private val lvLastVersionNumber = MutableLiveData<Unit>()

    fun appUpToDate(): LiveData<Unit> = lvAppUpToDate
    private val lvAppUpToDate = MutableLiveData<Unit>()

    fun noUpdateFound(): LiveData<Unit> = lvUpdateNotFound
    private val lvUpdateNotFound = MutableLiveData<Unit>()

    fun updateContentProgressBar(): LiveData<Pair<Int, Int>> = lvContentUpdateProgress
    private val lvContentUpdateProgress = MutableLiveData<Pair<Int, Int>>()

    fun hasContentForUpdate(): LiveData<Boolean> = lvShowUpdateProgressBar
    private val lvShowUpdateProgressBar = MutableLiveData<Boolean>()

    fun onSearchForUpdate() {
        coroutineScopeIO.launch {
            lvShowUpdateProgressBar.postValue(true)
            withContext(coroutineScopeIO.coroutineContext) {
                splashRepository.onSearchForContentUpdates(lvProgressValue = lvContentUpdateProgress)
            }
            lvShowUpdateProgressBar.postValue(false)
        }
    }

    fun onAppSuggestedVersionCheck() {
        when {
            BuildConfig.VERSION_CODE < splashRepository.onSearchForAppVersion() ->
                lvLastVersionNumber.postValue(Unit)
            else -> lvAppUpToDate.postValue(Unit)
        }
    }

    fun onValidateUser(screenName: String) =
        coroutineScopeIO.launch {
            lvStartNavigationFromFlow.postValue(
                Event(NavigationObject(screenName, splashRepository.isUserValid()))
            )
        }
}