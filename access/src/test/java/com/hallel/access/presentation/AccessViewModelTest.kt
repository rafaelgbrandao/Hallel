package com.hallel.access.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.hallel.access.helper.FormErrors
import com.hallel.access.repository.AccessRepository
import com.hallel.core_ui.navigation.NavigationHelper
import com.hallel.core_ui.navigation.NavigationObject
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AccessViewModelTest {

    @get:Rule
    val instantRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var accessRepository: AccessRepository

    private val viewModel by lazy {
        AccessViewModel(accessRepository, SCREEN_NAME)
    }

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun givenValidEmail_whenVerifyIfUserExist_thenAssertUserNotExist() {
        every {
            accessRepository.userAlreadyRegistered(any())
        } returns false

        viewModel.onVerifyIfUserExist(EMAIL)

        Assert.assertEquals(Unit, viewModel.userNotRegistered().value)

    }

    @Test
    fun givenValidEmail_whenVerifyIfUserExist_thenAssertUserExist() {
        every {
            accessRepository.userAlreadyRegistered(any())
        } returns true

        viewModel.onVerifyIfUserExist(EMAIL)

        Assert.assertEquals(
            NavigationObject(SCREEN_NAME),
            NavigationHelper.lvStartNavigationFromFlow.value
        )

    }

    @Test
    fun givenInvalidEmail_whenVerifyIfUserExist_thenAssertUserNotExist() {
        every {
            accessRepository.userAlreadyRegistered(any())
        } returns false

        viewModel.onVerifyIfUserExist(INVALID_EMAIL)

        Assert.assertEquals(Unit, viewModel.showInvalidEmailMessage().value)

    }

    @Test
    fun givenValidFormFields_whenValidateFormFields_thenAssertAllFieldsAreValid() {
        viewModel.onValidateFormFields(
            name = NAME,
            phone = PHONE,
            birthday = BIRTHDAY,
            email = EMAIL,
            isPrivacyPolicyChecked = true
        )

        Assert.assertEquals(Unit, viewModel.registerNewUserAfterValidateFormFields().value)
    }

    @Test
    fun givenValidFormFields_whenValidateFormFields_thenAssertAllFieldsAreInvalid() {
       viewModel.onValidateFormFields(
            name = INVALID_NAME,
            phone = INVALID_PHONE,
            birthday = INVALID_BIRTHDAY,
            email = INVALID_EMAIL,
            isPrivacyPolicyChecked = false
        )

        Assert.assertEquals(5, viewModel.showFormErrors().value?.size)
        Assert.assertEquals(true, viewModel.showFormErrors().value?.contains(FormErrors.INVALID_EMAIL))
        Assert.assertEquals(true, viewModel.showFormErrors().value?.contains(FormErrors.INVALID_NAME))
        Assert.assertEquals(true, viewModel.showFormErrors().value?.contains(FormErrors.INVALID_PHONE))
        Assert.assertEquals(true, viewModel.showFormErrors().value?.contains(FormErrors.INVALID_BIRTHDAY))
        Assert.assertEquals(true, viewModel.showFormErrors().value?.contains(FormErrors.PRIVACY_POLICE_NOT_CHECKED))
    }

    @Test
    fun givenValidFormFields_whenRegisterNewUser_thenAssertSuccessOnRegisterUser() {
        every {
            accessRepository.registerNewUser(any(), any(), any(), any())
        } returns true

        viewModel.registerNewUser(
            name = NAME,
            phone = PHONE,
            birthday = BIRTHDAY,
            email = EMAIL
        )

        Assert.assertEquals(
            NavigationObject(SCREEN_NAME),
            NavigationHelper.lvStartNavigationFromFlow.value
        )
    }

    @Test
    fun givenValidFormFields_whenRegisterNewUser_thenAssertErrorOnRegisterUser() {
        every {
            accessRepository.registerNewUser(any(), any(), any(), any())
        } returns false

        viewModel.registerNewUser(
            name = NAME,
            phone = PHONE,
            birthday = BIRTHDAY,
            email = EMAIL
        )

        Assert.assertEquals(Unit, viewModel.showErrorOnRegisterNewUser().value)
    }

    companion object {
        private const val SCREEN_NAME = "Fake Screen Name"

        private const val NAME = "Abc Da Silva 123"
        private const val PHONE = "(12) 11111-1111"
        private const val BIRTHDAY = "12/12/2000"
        private const val EMAIL = "teste@teste.com"

        private const val INVALID_NAME = "A"
        private const val INVALID_PHONE = "(12) 11111-1"
        private const val INVALID_BIRTHDAY = "12/12/2019"
        private const val INVALID_EMAIL = "teste@teste"
    }
}