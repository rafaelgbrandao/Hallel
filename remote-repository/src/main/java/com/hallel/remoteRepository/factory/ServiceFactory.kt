package com.hallel.remoteRepository.factory

import retrofit2.Retrofit

object ServiceFactory {

    fun <T> createService(retrofit: Retrofit, javaClass: Class<T>): T {
        return retrofit.create(javaClass)
    }
}