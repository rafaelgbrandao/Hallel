package com.hallel.access.repository

interface AccessRepository {

    suspend fun userAlreadyRegistered(userEmail: String): Boolean

    suspend fun registerNewUser(name: String, email: String, phone: String, birthday: String): Boolean
}