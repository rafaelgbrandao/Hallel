package com.hallel.hallelsomevida.di

import com.hallel.hallelsomevida.MainViewModel
import org.koin.dsl.module

val appmodule = module {

    single { MainViewModel() }
}