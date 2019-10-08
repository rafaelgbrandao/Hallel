package com.hallel.splash.repository

import com.hallel.localrepository.user.UserRepository

class SplashRepositoryImpl(private val userLocalRepository: UserRepository): SplashRepository {

    //TODO Create repository to get data

    override fun onSearchForAppVersion(): Int {
        return 0
    }

    override fun onSearchForContentUpdates(): Boolean {
        return false
    }

    override suspend fun isUserValid(): Boolean {
        return userLocalRepository.getUser()
    }
}