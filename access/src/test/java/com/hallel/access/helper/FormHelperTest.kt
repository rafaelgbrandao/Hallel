package com.hallel.access.helper

import org.junit.Assert.assertEquals
import org.junit.Test

class FormHelperTest {

    companion object {
        private const val VALID_EMAIL = "teste@teste.com"
        private const val INVALID_EMAIL = "teste.teste.com"
        private const val VALID_MAX_BIRTHDAY = "12/12/2000"
        private const val VALID_MIN_BIRTHDAY = "12/12/1919"
        private const val INVALID_BIRTHDAY = "40/40/2070"
        private const val INVALID_MIN_BIRTHDAY = "12/12/1900"
        private const val INVALID_MAX_BIRTHDAY = "12/12/2019"
        private const val VALID_PHONE_NUMBER = "(19) 99999-9999"
        private const val INVALID_PHONE_NUMBER = "(11) 1234-1232"
        private const val VALID_NAME = "Teste da Silva Teste"
        private const val INVALID_NAME = "A"
    }

    @Test
    fun givenEmail_WhenValidate_ThenAssertEmailIsValid() {
        assertEquals(true, isValidEmail(VALID_EMAIL))
    }

    @Test
    fun givenInvalidEmail_WhenValidate_ThenAssertEmailIsInvalid() {
        assertEquals(false, isValidEmail(INVALID_EMAIL))
        assertEquals(false, isValidEmail(""))
    }

    @Test
    fun givenBirthday_WhenValidate_ThenAssertBirthdayIsValid() {
        assertEquals(true, isValidBirthday(VALID_MAX_BIRTHDAY))
        assertEquals(true, isValidBirthday(VALID_MIN_BIRTHDAY))
    }

    @Test
    fun givenInvalidsBirthdays_WhenValidate_ThenAssertBirthdayIsInvalid() {
        assertEquals(false, isValidBirthday(INVALID_BIRTHDAY))
        assertEquals(false, isValidBirthday(INVALID_MIN_BIRTHDAY))
        assertEquals(false, isValidBirthday(INVALID_MAX_BIRTHDAY))
        assertEquals(false, isValidBirthday(""))
    }

    @Test
    fun givenPhone_WhenValidate_ThenAssertPhoneIsValid() {
        assertEquals(true, isValidPhone(VALID_PHONE_NUMBER))
    }

    @Test
    fun givenInvalidsPhone_WhenValidate_ThenAssertPhoneIsInvalid() {
        assertEquals(false, isValidPhone(INVALID_PHONE_NUMBER))
        assertEquals(false, isValidPhone(""))
    }

    @Test
    fun givenName_WhenValidate_ThenAssertNameIsValid() {
        assertEquals(true, isValidName(VALID_NAME))
    }

    @Test
    fun givenInvalidsName_WhenValidate_ThenAssertNameIsInvalid() {
        assertEquals(false, isValidName(INVALID_NAME))
        assertEquals(false, isValidName(""))
    }

    @Test
    fun givenValidDate_WhenValidateDateRange_ThenAssertDateWasConvertedSuccess() {
        assertEquals(true, isBirthdayRangeValid(VALID_MAX_BIRTHDAY))
    }

    @Test
    fun givenInvalidDate_WhenValidateDateRange_ThenAssertDateConversionFailed() {
        assertEquals(false, isBirthdayRangeValid(INVALID_MIN_BIRTHDAY))
    }

    @Test
    fun givenWrongDateFormat_WhenValidateDateRange_ThenAssertDateParseError() {
        assertEquals(false, isBirthdayRangeValid("01-02-2000"))
    }
}