package com.hallel.splash.repository

class SplashRepositoryImpl: SplashRepository {

    //TODO Create repository to get data

    override fun onSearchForAppVersion(): Int {
        return 0
    }

    override fun onSearchForContentUpdates(): Boolean {
        return false
    }

    override suspend fun isUserValid(): Boolean {
        return false
    }
}