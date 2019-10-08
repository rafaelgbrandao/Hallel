package com.hallel.splash.repository

interface SplashRepository {

    fun onSearchForAppVersion(): Int

    fun onSearchForContentUpdates(): Boolean

    suspend fun isUserValid(): Boolean
}