package com.hallel.splash.repository

import com.hallel.localrepository.dao.UserDao
import com.hallel.remoterepository.source.UpdateRemoteSource
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow

class SplashRepositoryImpl(
    private val userDao: UserDao,
    private val updateRemoteRepository: UpdateRemoteSource
): SplashRepository {

    //TODO Create updateLocalRepository to get data
    @UseExperimental(FlowPreview::class)
    override val onSearchForContentUpdates = flow {
        var count = 0
        val total = 10
        while (count < total) {
            delay(500)
            count += 5
            emit(Pair(count, total))
        }
            /*val content = updateRemoteRepository.getUpdateContent()
            when {
                content.isNotEmpty() -> {
                    lvShowProgressBar.postValue(true)
                    var count = 0
                    var total = content.size
                    content.forEach {
                        Handler().postDelayed(Runnable {
                            count += 1
                        }, 500)
                        lvProgressValue.postValue(Pair(count, total))
                    }
                    lvShowProgressBar.postValue(false)
                }
                else -> lvShowProgressBar.postValue(false)
            }*/
    }

    override fun onSearchForAppVersion(): Int {
        //return updateRemoteRepository.getAppVersion()
        return 0
    }

    override suspend fun isUserValid(): Boolean {
        return userDao.getUser() != null
    }
}