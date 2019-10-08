package com.hallel.localrepository.user

import com.hallel.localrepository.entity.User

interface UserRepository {

    fun getUser(): Boolean

    fun updateUser(user: User)

    fun deleteUser(user: User)

}