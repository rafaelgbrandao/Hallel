package com.hallel.hallelsomevida

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.navigation.findNavController
import com.hallel.core.extensions.observe
import com.hallel.core_ui.base.BaseActivity
import com.hallel.hallelsomevida.navigation.NavigationManager.getNavigationId
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initObservers()
    }

    private fun initObservers() {
        viewModel.startNavigationFromClick().observe(this) {
            findNavController(R.navigation.nav_graph).navigate(it)
        }

        viewModel.startNavigationFromFlow().observe(this) {
            when (val navId = getNavigationId(it)) {
                0 -> showToast("No id")
                else -> findNavController(R.id.nav_host_fragment).navigate(navId)
            }
        }

        viewModel.startNavigationFromDeepLink().observe(this) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
            startActivity(intent)
        }
    }
}