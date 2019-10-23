package com.hallel.localrepository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hallel.localrepository.dao.PartnerDao
import com.hallel.localrepository.dao.UserDao
import com.hallel.localrepository.entity.Partner
import com.hallel.localrepository.entity.User

@Database(entities = [User::class, Partner::class], version = 2)

abstract class HallelDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun partnerDao(): PartnerDao
}