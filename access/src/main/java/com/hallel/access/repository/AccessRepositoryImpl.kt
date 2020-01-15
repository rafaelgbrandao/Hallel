package com.hallel.access.repository

import com.hallel.core.utils.ResultWrapper
import com.hallel.localrepository.dao.UserDao
import com.hallel.localrepository.entity.User
import com.hallel.remoterepository.source.UserRemoteSouce
import java.lang.Exception

class AccessRepositoryImpl(
    private val userRemoteSource: UserRemoteSouce,
    private val userLocalRepository: UserDao
) : AccessRepository {

    override suspend fun registerNewUser(name: String, email: String, phone: String, birthday: String): ResultWrapper<Boolean> {
        val newUser = User(
            userId = 1,
            userName = name,
            userEmail = email,
            userBirthday = birthday,
            userPhone = phone,
            isSent = 0
        )
        return try {
            val isUserSaved = userLocalRepository.updateUser(newUser) > 0
            ResultWrapper.Success(isUserSaved)
        } catch (exception: Exception) {
            ResultWrapper.Error(error = exception)
        }

        /*return userRemoteSource.registerNewUser(
            user = UserRequestObject(
                name = name,
                email = email,
                phone = phone,
                birthday = birthday
            )
        )*/
    }

    override suspend fun userAlreadyRegistered(userEmail: String): Boolean {
        //return userRemoteSource.login(userEmail)
        return false
    }
}