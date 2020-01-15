package com.hallel.home.repository

import com.hallel.core.utils.ResultWrapper
import com.hallel.localrepository.entity.EventContent
import com.hallel.localrepository.entity.Participant
import com.hallel.localrepository.entity.Partner

interface HomeRepository {

    suspend fun getPartners(): ResultWrapper<List<Partner>>

    suspend fun getEventParticipants(): ResultWrapper<List<Participant>>

    suspend fun getEventContent(eventId: Int): ResultWrapper<EventContent?>
}