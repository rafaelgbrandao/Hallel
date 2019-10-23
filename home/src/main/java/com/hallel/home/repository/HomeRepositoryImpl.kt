package com.hallel.home.repository

import com.hallel.localrepository.dao.PartnerDao
import com.hallel.localrepository.entity.Partner

class HomeRepositoryImpl(private val partnerLocalRepository: PartnerDao): HomeRepository {

    override suspend fun getPartners(): List<Partner> {
        return partnerLocalRepository.getAllPartners()
    }

    private fun mockPartnerList() = listOf(
        Partner(
            id = 1,
            name = "Teste",
            link = "https://www.cocacola.com.br",
            image = "https://www.cocacola.com.br/content/dam/GO/coca-cola/brazil/Onebrand/logo_coca.png"
        )
    )
}