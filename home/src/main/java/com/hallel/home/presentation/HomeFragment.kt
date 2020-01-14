package com.hallel.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.hallel.core.extensions.observe
import com.hallel.core_ui.base.BaseFragment
import com.hallel.core_ui.extensions.gone
import com.hallel.home.R
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment: BaseFragment() {

    private val viewModel by viewModel<HomeViewModel>()

    private val eventId by lazy {
        arguments?.getInt("EVENT_ID") ?: 1
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setActionBarVisible(true)
        configureViews()
        initObservers()
        viewModel.onLoadEventContent(eventId)
    }

    private fun configureViews() {
        homeRVParticipants.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        homeRVSponsors.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun initObservers() {
        viewModel.setSponsorsAdapter().observe(this) {
            homeRVSponsors.adapter = HomeSponsorAdapter(it)
        }

        viewModel.showSponsorsError().observe(this) {
            homeRVSponsors.gone()
        }

        viewModel.setParticipantsAdapter().observe(this) {
            homeRVParticipants.adapter = HomeParticipantsAdapter(it)
        }

        viewModel.showParticipantsError().observe(this) {
            homeRVParticipants.gone()
        }

        viewModel.showEventNotAvailableMessage().observe(this) {
            showToast("No message available")
            homeRVParticipants.gone()
            homeRVSponsors.gone()
        }

        viewModel.eventIsAvailable().observe(this) {
            homeTxtLogo.text = it.eventTitle
            homeTxtSubtitle.text = it.eventSubtitle
            viewModel.onLoadPartners()
            viewModel.onLoadParticipants()
        }
    }
}