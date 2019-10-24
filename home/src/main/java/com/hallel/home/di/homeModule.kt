package com.hallel.home.di

import com.hallel.home.presentation.HomeFragment
import com.hallel.home.presentation.HomeViewModel
import com.hallel.home.repository.HomeRepository
import com.hallel.home.repository.HomeRepositoryImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

private const val SCREEN_NAME = "screen_name"

val homeModule = module {

    single(named(name = SCREEN_NAME)) { HomeFragment::class.java.name }

    single { HomeViewModel(get(), get(named(name = SCREEN_NAME))) }

    single { HomeRepositoryImpl(get(), get()) as HomeRepository }

}