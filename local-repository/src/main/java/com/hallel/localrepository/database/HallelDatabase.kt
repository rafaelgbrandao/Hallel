package com.hallel.localrepository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hallel.localrepository.dao.UserDao
import com.hallel.localrepository.entity.User

@Database(entities = [User::class], version = 1)

abstract class HallelDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao
}