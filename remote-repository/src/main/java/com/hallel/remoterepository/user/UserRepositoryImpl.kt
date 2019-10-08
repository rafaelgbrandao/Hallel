package com.hallel.remoterepository.user

class UserRepositoryImpl(private val userRemoteSource: UserRemoteSouce): UserRepository {

    override fun login(userEmail: String): Boolean {
        //return userRemoteSource.login(userEmail)
        return false
    }

    override fun registerNewUser(name: String, email: String, birthDay: String): Int {
        //return userRemoteSource.registerNewUser()
        return 0
    }
}