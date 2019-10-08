package com.hallel.splash.repository

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import com.hallel.localrepository.user.UserRepository
import com.hallel.remoterepository.update.UpdateRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashRepositoryImpl(
    private val userLocalRepository: UserRepository,
    private val updateRemoteRepository: UpdateRepository
): SplashRepository {

    //TODO Create updateLocalRepository to get data
    override fun onSearchForContentUpdates(
        lvShowProgressBar: MutableLiveData<Boolean>,
        lvProgressValue: MutableLiveData<Pair<Int, Int>>
    ) {
        lvShowProgressBar.postValue(true)
        var count = 0
        val total = 100
        CoroutineScope(Dispatchers.IO).launch {
            while (count < total) {
                delay(500)
                count += 5
                lvProgressValue.postValue(Pair(count, total))
            }
            lvShowProgressBar.postValue(false)
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
    }

    override fun onSearchForAppVersion(): Int {
        return 0
    }

    override suspend fun isUserValid(): Boolean {
        return userLocalRepository.getUser()
    }
}