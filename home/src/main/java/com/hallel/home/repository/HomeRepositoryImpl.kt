package com.hallel.home.repository

import com.hallel.localrepository.dao.ParticipantDao
import com.hallel.localrepository.dao.PartnerDao
import com.hallel.localrepository.entity.Participant
import com.hallel.localrepository.entity.Partner

class HomeRepositoryImpl(
    private val partnerLocalRepository: PartnerDao,
    private val participantLocalRepository: ParticipantDao
): HomeRepository {

    override suspend fun getEventParticipants(): List<Participant> {
        return mockParticipantList()
        //return participantLocalRepository.getParticipants(eventParticipants)
    }

    override suspend fun getPartners(): List<Partner> {
        return mockPartnerList()
        //return partnerLocalRepository.getAllPartners()
    }

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