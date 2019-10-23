package com.hallel.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hallel.core.extensions.observe
import com.hallel.core_ui.base.BaseFragment
import com.hallel.core_ui.extensions.gone
import com.hallel.home.R
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment: BaseFragment() {

    private val viewModel by viewModel<HomeViewModel>()

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
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.onLoadPartners()
        }
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
    }
}