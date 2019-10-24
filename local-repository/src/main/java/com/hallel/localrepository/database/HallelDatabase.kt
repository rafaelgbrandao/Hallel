package com.hallel.localrepository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hallel.localrepository.dao.*
import com.hallel.localrepository.entity.*

@Suppress("SpellCheckingInspection")
@Database(
    entities = [User::class, Partner::class, Participant::class, Event::class, EventContent::class],
    version = 1
)

abstract class HallelDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun partnerDao(): PartnerDao

    abstract fun participantDao(): ParticipantDao

    abstract fun eventDao(): EventDao

    abstract fun eventContentDao(): EventContentDao
}