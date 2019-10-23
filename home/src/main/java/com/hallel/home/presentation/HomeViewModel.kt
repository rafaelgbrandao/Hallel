package com.hallel.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hallel.core_ui.base.BaseViewModel
import com.hallel.home.repository.HomeRepository
import com.hallel.localrepository.entity.Partner

class HomeViewModel(
    private val homeRepository: HomeRepository,
    private val screenName: String
): BaseViewModel() {

    fun setSponsorsAdapter(): LiveData<List<Partner>> = lvSponsorsList
    private val lvSponsorsList = MutableLiveData<List<Partner>>()

    fun showSponsorsError(): LiveData<Unit> = lvSponsorsListError
    private val lvSponsorsListError = MutableLiveData<Unit>()

    suspend fun onLoadPartners() {
        val partnerList = homeRepository.getPartners()
        when {
            partnerList.isEmpty() -> lvSponsorsListError.postValue(Unit)
            else -> lvSponsorsList.postValue(partnerList)
        }
    }
}