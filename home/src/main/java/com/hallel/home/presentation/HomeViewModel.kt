package com.hallel.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hallel.core_ui.base.BaseViewModel
import com.hallel.home.repository.HomeRepository
import com.hallel.localrepository.entity.Participant
import com.hallel.localrepository.entity.Partner

class HomeViewModel(
    private val homeRepository: HomeRepository,
    private val screenName: String
): BaseViewModel() {

    fun setSponsorsAdapter(): LiveData<List<Partner>> = lvSponsorsList
    private val lvSponsorsList = MutableLiveData<List<Partner>>()

    fun showSponsorsError(): LiveData<Unit> = lvSponsorsListError
    private val lvSponsorsListError = MutableLiveData<Unit>()

    fun setParticipantsAdapter(): LiveData<List<Participant>> = lvParticipantsList
    private val lvParticipantsList = MutableLiveData<List<Participant>>()

    fun showParticipantsError(): LiveData<Unit> = lvParticipantsListError
    private val lvParticipantsListError = MutableLiveData<Unit>()

    suspend fun onLoadPartners() {
        val partnerList = homeRepository.getPartners()
        when {
            partnerList.isEmpty() -> lvSponsorsListError.postValue(Unit)
            else -> lvSponsorsList.postValue(partnerList)
        }
    }

    suspend fun onLoadParticipants() {
        val participantList = homeRepository.getEventParticipants()
        when {
            participantList.isEmpty() -> lvParticipantsListError.postValue(Unit)
            else -> lvParticipantsList.postValue(participantList)
        }
    }
}