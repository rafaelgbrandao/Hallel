package com.hallel.remoterepository.update

import kotlinx.coroutines.Deferred
import retrofit2.http.POST

interface UpdateRemoteSource {

    @POST("Hallel/scripts/Hallel.php")
    fun getContent(): Deferred<List<String>>

    @POST("Hallel/scripts/Hallel.php")
    fun getAppVersion(): Deferred<Int>
}