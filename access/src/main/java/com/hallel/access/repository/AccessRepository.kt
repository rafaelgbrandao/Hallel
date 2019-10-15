package com.hallel.access.repository

interface AccessRepository {

    fun userAlreadyRegistered(userEmail: String): Boolean

    fun registerNewUser(name: String, email: String, phone: String, birthday: String): Boolean
}