package com.hallel.home.repository

import com.hallel.core.utils.ResultWrapper
import com.hallel.localrepository.dao.EventContentDao
import com.hallel.localrepository.dao.ParticipantDao
import com.hallel.localrepository.dao.PartnerDao
import com.hallel.localrepository.entity.EventContent
import com.hallel.localrepository.entity.Participant
import com.hallel.localrepository.entity.Partner
import java.lang.Exception

class HomeRepositoryImpl(
    private val partnerDao: PartnerDao,
    private val participantDao: ParticipantDao,
    private val eventContentDao: EventContentDao
): HomeRepository {

    override suspend fun getEventContent(eventId: Int): ResultWrapper<EventContent?> {
        return try{
            ResultWrapper.Success(mockEventContent())
        } catch (e: Exception) {
            ResultWrapper.Error(error = e)
        }
        //return eventContentDao.getEventContent(eventId)
    }

    override suspend fun getEventParticipants(): ResultWrapper<List<Participant>> {
        return try{
            ResultWrapper.Success(mockParticipantList())
        } catch (e: Exception) {
            ResultWrapper.Error(error = e)
        }
        //return participantDao.getParticipants(eventParticipants)
    }

    override suspend fun getPartners(): ResultWrapper<List<Partner>> {
        return try{
            ResultWrapper.Success(mockPartnerList())
        } catch (e: Exception) {
            ResultWrapper.Error(error = e)
        }
        //return partnerDao.getAllPartners()
    }


    private fun mockEventContent() = EventContent(
        id = 1,
        eventId = 1,
        eventTitle = "Title",
        eventSubtitle = "Subtitle"
    )

    private fun mockPartnerList() = listOf(
        Partner(
            id = 1,
            name = "Teste",
            link = "https://www.cocacola.com.br",
            image = "https://www.cocacola.com.br/content/dam/GO/coca-cola/brazil/Onebrand/logo_coca.png"
        )
    )

    private fun mockParticipantList() = listOf(
        Participant(
            id = 1,
            name = "Thiago Brado",
            type = 1,
            image = "http://img.cancaonova.com/cnimages/canais/uploads/sites/15/2016/10/750x430-CapaMusica-002-3.jpg"
        ),
        Participant(
            id = 2,
            name = "Adriana Arydes",
            type = 1,
            image = "https://www.culturamix.com/wp-content/gallery/cantora-catolica-adriana-6/Cantora-Cat%C3%B3lica-Adriana-15.JPG"
        )
    )
}