package com.hallel.access.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.hallel.access.helper.FormErrors
import com.hallel.access.repository.AccessRepository
import com.hallel.core.utils.ResultWrapper
import com.hallel.core_ui.helpers.lvStartNavigationFromFlow
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class AccessViewModelTest {

    @get:Rule
    val instantRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    @MockK
    private lateinit var accessRepository: AccessRepository

    private val viewModel by lazy {
        AccessViewModel(accessRepository, SCREEN_NAME)
    }

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun onFinish() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun givenValidEmail_whenVerifyIfUserExist_thenAssertUserNotExist() {
         coEvery {
             accessRepository.userAlreadyRegistered(any())
         } returns false

        viewModel.onVerifyIfUserExist(EMAIL)

        assertEquals(Unit, viewModel.userNotRegistered().value)
    }

    @Test
    fun givenValidEmail_whenVerifyIfUserExist_thenAssertUserExist() {
        coEvery {
            accessRepository.userAlreadyRegistered(any())
        } returns true

        viewModel.onVerifyIfUserExist(EMAIL)

        val navObject = lvStartNavigationFromFlow.value?.getContentIfNotHandled()
        assertEquals(SCREEN_NAME, navObject?.screenName)
        assertEquals(null, navObject?.extras)
    }

    @Test
    fun givenInvalidEmail_whenVerifyIfUserExist_thenAssertUserNotExist() {
        coEvery {
            accessRepository.userAlreadyRegistered(any())
        } returns false

        viewModel.onVerifyIfUserExist(INVALID_EMAIL)

        assertEquals(Unit, viewModel.showInvalidEmailMessage().value)

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

        assertEquals(Unit, viewModel.registerNewUserAfterValidateFormFields().value)
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

        assertEquals(5, viewModel.showFormErrors().value?.size)
        assertEquals(true, viewModel.showFormErrors().value?.contains(FormErrors.INVALID_EMAIL))
        assertEquals(true, viewModel.showFormErrors().value?.contains(FormErrors.INVALID_NAME))
        assertEquals(true, viewModel.showFormErrors().value?.contains(FormErrors.INVALID_PHONE))
        assertEquals(true, viewModel.showFormErrors().value?.contains(FormErrors.INVALID_BIRTHDAY))
        assertEquals(true, viewModel.showFormErrors().value?.contains(FormErrors.PRIVACY_POLICE_NOT_CHECKED))
    }

    @Test
    fun givenValidFormFields_whenRegisterNewUser_thenAssertSuccessOnRegisterUser() {
        coEvery {
            accessRepository.registerNewUser(any(), any(), any(), any())
        } returns ResultWrapper.Success(true)

        viewModel.registerNewUser(
            name = NAME,
            phone = PHONE,
            birthday = BIRTHDAY,
            email = EMAIL
        )

        val navObject = lvStartNavigationFromFlow.value?.getContentIfNotHandled()
        assertEquals(SCREEN_NAME, navObject?.screenName)
        assertEquals(null, navObject?.extras)
    }

    @Test
    fun givenValidFormFields_whenRegisterNewUser_thenAssertErrorOnRegisterUser() {
        coEvery {
            accessRepository.registerNewUser(any(), any(), any(), any())
        } returns ResultWrapper.Success(false)

        viewModel.registerNewUser(
            name = NAME,
            phone = PHONE,
            birthday = BIRTHDAY,
            email = EMAIL
        )

        assertEquals(Unit, viewModel.showErrorOnRegisterNewUser().value)
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