package com.hallel.splash.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.hallel.core.extensions.observe
import com.hallel.core_ui.base.BaseFragment
import com.hallel.core_ui.extensions.gone
import com.hallel.core_ui.extensions.visible
import com.hallel.splash.R
import kotlinx.android.synthetic.main.fragment_splash.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment: BaseFragment() {

    private val viewModel by viewModel<SplashViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setActionBarVisible(false)
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startObservers()
        viewModel.onAppSuggestedVersionCheck()
    }

    private fun startObservers() {

        viewModel.showAppUpdateDialog().observe(this) {
            showSimpleDialog(
                title = "Nova versão disponível",
                message = "Deseja atualizar?",
                positiveButtonText = "Sim",
                positiveButtonListener = { Toast.makeText(context, "Positive Click", Toast.LENGTH_LONG).show() },
                negativeButtonText = "Não",
                negativeButtonListener = { searchForUpdates() }
            )
        }

        viewModel.appUpToDate().observe(this)  {
            searchForUpdates()
        }

        viewModel.noUpdateFound().observe(this) {
            validateUser()
        }

        viewModel.hasContentForUpdate().observe(this) { showProgress ->
            when {
                showProgress -> {
                    splashProgressBarContentSearch.gone()
                    splashProgressBarContentUpdate.visible()
                    splashTextViewUpdateProgress.text = "Atualizando"
                }
                else -> validateUser()
            }
        }

        viewModel.uptadeContentProgressBar().observe(this) { (currentProgress, maxValue) ->
            splashProgressBarContentUpdate.max = maxValue
            splashProgressBarContentUpdate.progress = currentProgress
        }
    }

    private fun searchForUpdates() {
        GlobalScope.launch { viewModel.onSearchForUpdate() }
    }

    private fun validateUser() {
        GlobalScope.launch {
            viewModel.onValidateUser()
        }
    }
}