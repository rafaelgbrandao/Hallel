package com.hallel.access.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hallel.core_ui.base.BaseFragment
import com.hallel.access.R
import com.hallel.access.helper.DATE_MASK
import com.hallel.access.helper.FormErrors
import com.hallel.access.helper.PHONE_MASK
import com.hallel.core.extensions.observe
import com.hallel.core_ui.extensions.applySingleMask
import com.hallel.core_ui.extensions.getInputText
import com.hallel.core_ui.extensions.setTextChangeListener
import com.hallel.core_ui.extensions.visible
import kotlinx.android.synthetic.main.fragment_access.*
import org.koin.android.viewmodel.ext.android.viewModel

class AccessFragment: BaseFragment() {

    private val viewModel by viewModel<AccessViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_access, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFields()
        initObservers()
        setSendButtonListener {
            viewModel.onVerifyIfUserExist(userEmail = accessTxtInputEditTextEmail.getInputText())
        }
    }

    private fun initFields() {
        accessTxtInputEditTextPhone.applySingleMask(PHONE_MASK) { accessTxtInputLayoutPhone.error = "" }
        accessTxtInputEditTextBirthday.applySingleMask(DATE_MASK) { accessTxtInputLayoutBirthday.error = "" }
        accessTxtInputEditTextName.setTextChangeListener(onChange = { accessTxtInputLayoutName.error = "" })
        accessTxtInputEditTextEmail.setTextChangeListener(onChange = { accessTxtInputLayoutEmail.error = "" })
    }

    private fun initObservers() {
        viewModel.userNotRegistered().observe(this) {
            enableFormViews()
            setSendButtonListener {
                viewModel.onValidateFormFields(
                    name = accessTxtInputEditTextName.getInputText(),
                    email = accessTxtInputEditTextEmail.getInputText(),
                    phone = accessTxtInputEditTextPhone.getInputText(),
                    birthday = accessTxtInputEditTextBirthday.getInputText(),
                    isPrivacyPolicyChecked = accessCheckBoxAcceptPrivacyPolicy.isChecked
                )
            }
        }

        viewModel.showFormErrors().observe(this) { errorList ->
            errorList.forEach {
                when (it) {
                    FormErrors.INVALID_BIRTHDAY -> accessTxtInputLayoutBirthday.error = getString(R.string.invalid_birthday)
                    FormErrors.INVALID_PHONE -> accessTxtInputLayoutPhone.error = getString(R.string.invalid_phone)
                    FormErrors.INVALID_NAME -> accessTxtInputLayoutName.error = getString(R.string.invalid_name)
                    FormErrors.INVALID_EMAIL -> accessTxtInputLayoutEmail.error = getString(R.string.invalid_email)
                    FormErrors.PRIVACY_POLICE_NOT_CHECKED -> showToast(getString(R.string.unchecked_privacy_policy_text))
                }
            }
        }

        viewModel.showInvalidEmailMessage().observe(this) {
            accessTxtInputLayoutEmail.error = getString(R.string.invalid_email)
        }

        viewModel.showErrorOnRegisterNewUser().observe(this) {
            showToast(getString(R.string.fail_on_register_new_user))
        }
    }

    private fun setSendButtonListener(listener: () -> Unit) {
        accessBtnSend.setOnClickListener { listener() }
    }

    private fun enableFormViews() {
        showToast(getString(R.string.user_not_registered))
        accessTxtTitle.text = getString(R.string.register_title)
        accessTxtInputLayoutName.visible()
        accessTxtInputLayoutPhone.visible()
        accessTxtInputLayoutBirthday.visible()
        accessCheckBoxAcceptPrivacyPolicy.visible()
    }
}