package com.hallel.splash.repository

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow

interface SplashRepository {

    fun onSearchForAppVersion(): Int

    @UseExperimental(FlowPreview::class)
    val onSearchForContentUpdates: Flow<Pair<Int, Int>>

    suspend fun isUserValid(): Boolean
}