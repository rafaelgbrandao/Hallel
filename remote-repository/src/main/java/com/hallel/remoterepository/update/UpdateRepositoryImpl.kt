package com.hallel.remoterepository.update

class UpdateRepositoryImpl(private val updateRepository: UpdateRemoteSource): UpdateRepository {

    override fun getAppVersion(): Int {
        //return updateRepository.getAppVersion()
        return 0
    }

    override fun getUpdateContent(): List<UpdateResponse> {
        //return updateRepository.getContent()
        return emptyList()
    }
}