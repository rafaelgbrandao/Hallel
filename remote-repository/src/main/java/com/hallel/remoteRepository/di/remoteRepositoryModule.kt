package com.hallel.remoteRepository.di

import com.hallel.remoteRepository.factory.ServiceFactory
import com.hallel.remoteRepository.update.UpdateRemoteSource
import com.hallel.remoteRepository.update.UpdateRepository
import com.hallel.remoteRepository.update.UpdateRepositoryImpl
import com.hallel.remoteRepository.user.UserRemoteSouce
import com.hallel.remoteRepository.user.UserRepository
import com.hallel.remoteRepository.user.UserRepositoryImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "http://www.brasilcristao.com.br/app/"

val remoteRepositoryModule = module {

    single { UpdateRepositoryImpl(get()) as UpdateRepository }

    single { UserRepositoryImpl(get()) as UserRepository }

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