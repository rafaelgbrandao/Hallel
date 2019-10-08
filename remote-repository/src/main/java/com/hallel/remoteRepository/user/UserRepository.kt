package com.hallel.remoteRepository.user

interface UserRepository {

    fun login(userEmail: String): Boolean

    fun registerNewUser(name: String, email: String, birthDay: String): Int
}
