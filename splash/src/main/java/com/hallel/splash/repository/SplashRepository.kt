package com.hallel.splash.repository

import androidx.lifecycle.MutableLiveData

interface SplashRepository {

    fun onSearchForAppVersion(): Int

    suspend fun onSearchForContentUpdates(
        lvShowProgressBar: MutableLiveData<Boolean>,
        lvProgressValue: MutableLiveData<Pair<Int, Int>>
    )

    suspend fun isUserValid(): Boolean
}