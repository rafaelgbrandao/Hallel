package com.hallel.localrepository.user

import com.hallel.localrepository.dao.UserDao
import com.hallel.localrepository.entity.User

class UserRepositoryImpl(private val userDao: UserDao): UserRepository {

    override fun getUser(): Boolean {
        return userDao.getUser() != null
    }

    override fun updateUser(user: User): Boolean {
        return userDao.updateUser(user) > 0
    }

    override fun deleteUser(user: User) {
        return userDao.deleteUser(user)
    }
}