package com.hallel.access.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hallel.core_ui.base.BaseFragment
import com.hallel.access.R
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
        initObservers()
    }

    private fun initObservers() {
        viewModel.
    }
}