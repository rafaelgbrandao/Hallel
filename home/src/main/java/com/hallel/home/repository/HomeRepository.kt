package com.hallel.home.repository

import com.hallel.localrepository.entity.Participant
import com.hallel.localrepository.entity.Partner

interface HomeRepository {

    suspend fun getPartners(): List<Partner>

    suspend fun getEventParticipants(): List<Participant>
}