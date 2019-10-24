package com.hallel.localrepository.di

import com.hallel.localrepository.BuildConfig
import com.hallel.localrepository.database.HallelDatabase
import com.hallel.localrepository.factory.DatabaseFactory
import com.hallel.localrepository.factory.createRoomDb
import com.hallel.localrepository.user.UserRepository
import com.hallel.localrepository.user.UserRepositoryImpl
import org.koin.dsl.module

val localRepositoryModule = module {

    single { DatabaseFactory(get()) }

    single { createRoomDb<HallelDatabase>(get(), BuildConfig.DATABASE_NAME, get()) }

    single { get<HallelDatabase>().userDao() }

    single { get<HallelDatabase>().partnerDao() }

    single { get<HallelDatabase>().participantDao() }

    single { get<HallelDatabase>().eventDao() }

    single { get<HallelDatabase>().eventContentDao() }

    single<UserRepository>{ UserRepositoryImpl(get()) }
}