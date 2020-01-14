package com.hallel.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hallel.core_ui.base.BaseViewModel
import com.hallel.home.repository.HomeRepository
import com.hallel.localrepository.entity.EventContent
import com.hallel.localrepository.entity.Participant
import com.hallel.localrepository.entity.Partner
import kotlinx.coroutines.launch

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

    fun eventIsAvailable(): LiveData<EventContent> = lvEventAvailable
    private val lvEventAvailable = MutableLiveData<EventContent>()

    fun showEventNotAvailableMessage(): LiveData<Unit> = lvEventNotAvailable
    private val lvEventNotAvailable = MutableLiveData<Unit>()

    fun onLoadPartners() {
        coroutineScopeIO.launch {
            val partnerList = homeRepository.getPartners()
            when {
                partnerList.isEmpty() -> lvSponsorsListError.postValue(Unit)
                else -> lvSponsorsList.postValue(partnerList)
            }
        }
    }

    fun onLoadParticipants() {
        coroutineScopeIO.launch {
            val participantList = homeRepository.getEventParticipants()
            when {
                participantList.isEmpty() -> lvParticipantsListError.postValue(Unit)
                else -> lvParticipantsList.postValue(participantList)
            }
        }
    }

    fun onLoadEventContent(eventId: Int) {
        coroutineScopeIO.launch {
            homeRepository.getEventContent(eventId)?.let {
                lvEventAvailable.postValue(it)
            } ?: lvEventNotAvailable.postValue(Unit)
        }
    }
}