package com.hallel.remoterepository.source

import com.hallel.remoterepository.request.UserRequestObject
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.POST

interface UserRemoteSouce {

    @POST("Hallel/scripts/user.php")
    fun login(@Body userEmail: String) : Deferred<Int>

    @POST("Hallel/scripts/user.php")
    fun registerNewUser(@Body user: UserRequestObject) : Deferred<Int>

}