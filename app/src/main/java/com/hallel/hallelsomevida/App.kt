package com.hallel.hallelsomevida

import android.app.Application
import com.hallel.hallelsomevida.di.appmodule
import com.hallel.localrepository.di.localRepositoryModule
import com.hallel.remoterepository.di.remoteRepositoryModule
import com.hallel.splash.di.splashModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    appmodule,
                    splashModule,
                    localRepositoryModule,
                    remoteRepositoryModule
                )
            )
        }
    }
}
