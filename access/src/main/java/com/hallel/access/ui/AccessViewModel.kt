package com.hallel.access.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hallel.access.helper.FormErrors
import com.hallel.access.helper.FormVO
import com.hallel.core_ui.base.BaseViewModel

class AccessViewModel: BaseViewModel() {

    fun showFormErrors(): LiveData<List<FormErrors>> = lvValidateFields
    private val lvValidateFields = MutableLiveData<List<FormErrors>>()

    fun showFormErrors(): LiveData<List<FormErrors>> = lvValidateFields
    private val lvValidateFields = MutableLiveData<List<FormErrors>>()

    private fun validateFieldInfo(formVO: FormVO, isPrivacyPolicyChecked:Boolean) {
        val errorList = mutableListOf<FormErrors>().apply {
            if (!isValidName(formVO.name)) add(FormErrors.INVALID_NAME)
            if (!isValidEmail(formVO.email)) add(FormErrors.INVALID_EMAIL)
            if (!isValidPhone(formVO.phone)) add(FormErrors.INVALID_PHONE)
            if (!isValidBirthday(formVO.birthday)) add(FormErrors.INVALID_BHIRTDAY)
            if (!isPrivacyPolicyChecked) add(FormErrors.PRIVAVY_POLICE_NOT_CHECKED)
        }

        lvValidateFields.postValue(errorList)
    }

    fun onSendFormRegistration() {

    }

    private fun isValidBirthday(birthday: String): Boolean {
        return false
    }

    private fun isValidPhone(phone: String): Boolean {
        return false
    }

    private fun isValidEmail(email: String): Boolean {
        return false
    }

    private fun isValidName(name: String): Boolean {
        return false
    }
}