package com.hallel.access.presentation

import com.hallel.core.base.BaseTest

class AccessViewModelTest: BaseTest() {

    /*

    fun userNotRegistered(): LiveData<Unit> = lvUserNotRegistered
    private val lvUserNotRegistered = MutableLiveData<Unit>()

    fun showInvalidEmailMessage(): LiveData<Unit> = lvInvalidEmail
    private val lvInvalidEmail = MutableLiveData<Unit>()

    fun showFormErrors(): LiveData<List<FormErrors>> = lvValidateFields
    private val lvValidateFields = MutableLiveData<List<FormErrors>>()

    fun showErrorOnRegisterNewUser(): LiveData<Unit> = lvErrorOnRegisterNewUser
    private val lvErrorOnRegisterNewUser = MutableLiveData<Unit>()

    fun onVerifyIfUserExist(userEmail: String) {
        if (isValidEmail(userEmail)) {
            when {
                accessRepository.userAlreadyRegistered(userEmail) -> buildNavigation()
                else -> lvUserNotRegistered.value = Unit
            }
        } else {
            lvInvalidEmail.value = Unit
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
            errorList.isEmpty() -> registerNewUser(name, email, phone, birthday)
            else -> lvValidateFields.postValue(errorList)
        }
    }

    private fun registerNewUser(name: String, email: String, phone: String, birthday: String) {
        when {
            accessRepository.registerNewUser(name, email, phone, birthday) -> buildNavigation()
            else -> lvErrorOnRegisterNewUser.postValue(Unit)
        }
    }

    private fun buildNavigation() {
        lvStartNavigationFromFlow.postValue(NavigationObject(screenName = screenName))
    }

     */
}