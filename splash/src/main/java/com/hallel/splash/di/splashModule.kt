package com.hallel.splash.di

import com.hallel.splash.presentation.SplashViewModel
import com.hallel.splash.repository.SplashRepository
import com.hallel.splash.repository.SplashRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val splashModule = module {

    viewModel { SplashViewModel(get()) }

    single<SplashRepository> { SplashRepositoryImpl() }
}