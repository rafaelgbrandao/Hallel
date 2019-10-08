package com.hallel.splash.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hallel.splash.BuildConfig
import com.hallel.splash.repository.SplashRepository

class SplashViewModel(private val splashRepository: SplashRepository): ViewModel() {

    fun showAppUpdateDialog(): LiveData<Unit> = lvLastVersionNumber
    private val lvLastVersionNumber = MutableLiveData<Unit>()

    fun appUpToDate(): LiveData<Unit> = lvAppUpToDate
    private val lvAppUpToDate = MutableLiveData<Unit>()

    fun showUpdateProgress(): LiveData<Unit> = lvUpdateValues
    private val lvUpdateValues = MutableLiveData<Unit>()

    fun noUpdateFound(): LiveData<Unit> = lvUpdateNotFound
    private val lvUpdateNotFound = MutableLiveData<Unit>()

    suspend fun onSearchForUpdate() {
        Log.v("Teste", "${splashRepository.isUserValid()}")
        /*when {
            splashRepository.onSearchForContentUpdates() -> lvUpdateValues.postValue(Unit)
            else -> lvUpdateNotFound.postValue(Unit)
        }*/
    }

    fun onAppSuggestedVersionCheck() {
        when {
            BuildConfig.VERSION_CODE < splashRepository.onSearchForAppVersion() ->
                lvLastVersionNumber.postValue(Unit)
            else -> lvAppUpToDate.postValue(Unit)

        }
    }

    suspend fun onValidateUser() = splashRepository.isUserValid()
}