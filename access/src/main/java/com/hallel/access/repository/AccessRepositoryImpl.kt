package com.hallel.access.repository

import com.hallel.localrepository.user.UserRepository
import com.hallel.remoterepository.request.UserRequestObject
import com.hallel.remoterepository.source.UserRemoteSouce

class AccessRepositoryImpl(
    private val userRemoteSource: UserRemoteSouce,
    private val userLocalRepository: UserRepository
) : AccessRepository {

    override fun registerNewUser(name: String, email: String, phone: String, birthday: String): Boolean {
        /*return userRemoteSource.registerNewUser(
            user = UserRequestObject(
                name = name,
                email = email,
                phone = phone,
                birthday = birthday
            )
        )*/
        return true
    }

    override fun userAlreadyRegistered(userEmail: String): Boolean {
        //return userRemoteSource.login(userEmail)
        return false
    }
}