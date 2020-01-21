package com.hallel.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hallel.core.utils.ResultWrapper
import com.hallel.core_ui.base.BaseViewModel
import com.hallel.home.repository.HomeRepository
import com.hallel.localrepository.entity.EventContent
import com.hallel.localrepository.entity.Participant
import com.hallel.localrepository.entity.Partner
import kotlinx.coroutines.launch

class HomeViewModel(
    private val homeRepository: HomeRepository,
    private val screenName: String
) : BaseViewModel() {

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
        viewModelScope.launch(dispatchers.IO) {
            when (val partnerList = homeRepository.getPartners()) {
                is ResultWrapper.Error -> handleErrors(partnerList.error)
                is ResultWrapper.Success ->
                    when {
                        partnerList.value.isEmpty() -> lvSponsorsListError.postValue(Unit)
                        else -> lvSponsorsList.postValue(partnerList.value)
                    }
            }
        }
    }

    fun onLoadParticipants() {
        viewModelScope.launch(dispatchers.IO) {
            when (val participantList = homeRepository.getEventParticipants()) {
                is ResultWrapper.Error -> handleErrors(participantList.error)
                is ResultWrapper.Success ->
                    when {
                        participantList.value.isEmpty() -> lvParticipantsListError.postValue(Unit)
                        else -> lvParticipantsList.postValue(participantList.value)
                    }
            }
        }
    }

    fun onLoadEventContent(eventId: Int) {
        viewModelScope.launch(dispatchers.IO) {
            when (val eventContent = homeRepository.getEventContent(eventId)) {
                is ResultWrapper.Error -> handleErrors(eventContent.error)
                is ResultWrapper.Success ->
                    eventContent.value?.let {
                        lvEventAvailable.postValue(it)
                    } ?: lvEventNotAvailable.postValue(Unit)
            }

        }
    }
}