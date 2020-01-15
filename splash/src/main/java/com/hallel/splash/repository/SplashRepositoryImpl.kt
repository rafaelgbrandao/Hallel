package com.hallel.splash.repository

import androidx.lifecycle.MutableLiveData
import com.hallel.localrepository.dao.UserDao
import com.hallel.remoterepository.source.UpdateRemoteSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashRepositoryImpl(
    private val userDao: UserDao,
    private val updateRemoteRepository: UpdateRemoteSource
): SplashRepository {

    //TODO Create updateLocalRepository to get data
    override suspend fun onSearchForContentUpdates(lvProgressValue: MutableLiveData<Pair<Int, Int>>) {
        var count = 0
        val total = 10
        while (count < total) {
            delay(500)
            count += 5
            lvProgressValue.postValue(Pair(count, total))
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