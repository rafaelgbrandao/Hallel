package com.hallel.access.repository

import com.hallel.core.utils.ResultWrapper

interface AccessRepository {

    suspend fun userAlreadyRegistered(userEmail: String): Boolean

    suspend fun registerNewUser(name: String, email: String, phone: String, birthday: String): ResultWrapper<Boolean>
}