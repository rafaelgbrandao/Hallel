package com.hallel.remoterepository.di

import com.hallel.remoterepository.factory.ServiceFactory
import com.hallel.remoterepository.source.UpdateRemoteSource
import com.hallel.remoterepository.source.UserRemoteSouce
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "http://www.brasilcristao.com.br/app/"

val remoteRepositoryModule = module {

    single { ServiceFactory.createService(get(), UserRemoteSouce::class.java) }

    single { ServiceFactory.createService(get(), UpdateRemoteSource::class.java) }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get(named("OkHttpClient")))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single(named("OkHttpClient")) {
        OkHttpClient
            .Builder()
            .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }
}