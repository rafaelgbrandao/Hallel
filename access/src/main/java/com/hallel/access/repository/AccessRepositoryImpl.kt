package com.hallel.access.repository

import com.hallel.localrepository.dao.UserDao
import com.hallel.localrepository.entity.User
import com.hallel.remoterepository.request.UserRequestObject
import com.hallel.remoterepository.source.UserRemoteSouce
import kotlinx.coroutines.*
import kotlin.coroutines.coroutineContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class AccessRepositoryImpl(
    private val userRemoteSource: UserRemoteSouce,
    private val userLocalRepository: UserDao
) : AccessRepository {

    override suspend fun registerNewUser(name: String, email: String, phone: String, birthday: String): Boolean {
        val newUser = User(
            userId = 1,
            userName = name,
            userEmail = email,
            userBirthday = birthday,
            userPhone = phone,
            isSent = 0
        )
        return saveNewUserLocal(newUser)
        /*return userRemoteSource.registerNewUser(
            user = UserRequestObject(
                name = name,
                email = email,
                phone = phone,
                birthday = birthday
            )
        )*/
    }

    private suspend fun saveNewUserLocal(newUser: User): Boolean {
        return suspendCoroutine {
            it.resume(userLocalRepository.updateUser(newUser) > 0)
        }
    }

    override suspend fun userAlreadyRegistered(userEmail: String): Boolean {
        //return userRemoteSource.login(userEmail)
        return false
    }
}