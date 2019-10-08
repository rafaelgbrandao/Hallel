package com.hallel.remoterepository.update

interface UpdateRepository {

    fun getAppVersion(): Int

    fun getUpdateContent(): List<UpdateResponse>
}
