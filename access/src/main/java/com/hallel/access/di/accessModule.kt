package com.hallel.access.di

import com.hallel.access.presentation.AccessFragment
import com.hallel.access.repository.AccessRepository
import com.hallel.access.repository.AccessRepositoryImpl
import com.hallel.access.presentation.AccessViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

private const val SCREEN_NAME =  "screenName"

val registerModule = module {

    single(named(name = SCREEN_NAME)) { AccessFragment::class.java.name }

    single { AccessViewModel(get(), get(named(name = SCREEN_NAME))) }

    single<AccessRepository> { AccessRepositoryImpl(get(), get()) }
}