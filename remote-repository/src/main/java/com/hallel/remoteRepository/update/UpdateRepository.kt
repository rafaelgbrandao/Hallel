package com.hallel.remoteRepository.update

interface UpdateRepository {

    fun getAppVersion(): Int

    fun getUpdateContent(): List<String>
}
