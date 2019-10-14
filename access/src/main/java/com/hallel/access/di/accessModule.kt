package com.hallel.access.di

import com.hallel.access.ui.AccessViewModel
import org.koin.dsl.module

val registerModule = module {

    single { AccessViewModel() }
}