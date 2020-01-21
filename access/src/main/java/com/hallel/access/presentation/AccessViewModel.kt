package com.hallel.access.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hallel.access.helper.*
import com.hallel.access.repository.AccessRepository
import com.hallel.core.utils.Event
import com.hallel.core.utils.ResultWrapper.*
import com.hallel.core_ui.base.BaseViewModel
import com.hallel.core_ui.helpers.lvStartNavigationFromFlow
import com.hallel.core_ui.navigation.NavigationObject
import kotlinx.coroutines.launch

class AccessViewModel(
    private val accessRepository: AccessRepository,
    private val screenName: String
) : BaseViewModel() {

    fun userNotRegistered(): LiveData<Unit> = lvUserNotRegistered
    private val lvUserNotRegistered = MutableLiveData<Unit>()

    fun showInvalidEmailMessage(): LiveData<Unit> = lvInvalidEmail
    private val lvInvalidEmail = MutableLiveData<Unit>()

    fun showFormErrors(): LiveData<List<FormErrors>> = lvFormFieldsHasError
    private val lvFormFieldsHasError = MutableLiveData<List<FormErrors>>()

    fun registerNewUserAfterValidateFormFields(): LiveData<Unit> = lvFormFieldsAreValid
    private val lvFormFieldsAreValid = MutableLiveData<Unit>()

    fun showErrorOnRegisterNewUser(): LiveData<Unit> = lvErrorOnRegisterNewUser
    private val lvErrorOnRegisterNewUser = MutableLiveData<Unit>()

    fun onVerifyIfUserExist(userEmail: String) {
        viewModelScope.launch(dispatchers.IO) {
            if (isValidEmail(userEmail)) {
                when {
                    accessRepository.userAlreadyRegistered(userEmail) -> buildNavigation()
                    else -> lvUserNotRegistered.postValue(Unit)
                }
            } else {
                lvInvalidEmail.postValue(Unit)
            }
        }
    }

    fun onValidateFormFields(
        name: String,
        email: String,
        phone: String,
        birthday: String,
        isPrivacyPolicyChecked: Boolean
    ) {
        val errorList = mutableListOf<FormErrors>().apply {
            if (!isValidName(name)) add(FormErrors.INVALID_NAME)
            if (!isValidEmail(email)) add(FormErrors.INVALID_EMAIL)
            if (!isValidPhone(phone)) add(FormErrors.INVALID_PHONE)
            if (!isValidBirthday(birthday)) add(FormErrors.INVALID_BIRTHDAY)
            if (!isPrivacyPolicyChecked) add(FormErrors.PRIVACY_POLICE_NOT_CHECKED)
        }.toList()
        when {
            errorList.isEmpty() -> lvFormFieldsAreValid.postValue(Unit)
            else -> lvFormFieldsHasError.postValue(errorList)
        }
    }

    fun registerNewUser(name: String, email: String, phone: String, birthday: String) {
        viewModelScope.launch(dispatchers.IO) {
            when (val result = accessRepository.registerNewUser(name, email, phone, birthday)) {
                is Error -> {handleErrors(result.error) }
                is Success -> {
                    when {
                        result.value -> buildNavigation()
                        else -> lvErrorOnRegisterNewUser.postValue(Unit)
                    }
                }
            }
        }
    }

    private fun buildNavigation() {
        lvStartNavigationFromFlow.postValue(Event(NavigationObject(screenName = screenName)))
    }
}