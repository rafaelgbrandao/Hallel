package com.hallel.splash

import android.app.job.JobScheduler
import com.hallel.splash.presentation.SplashViewModel
import kotlinx.coroutines.SupervisorJob
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    private val splashViewModel = SplashViewModel()

    @Test
    fun addition_isCorrect() {
        splashViewModel.job = SupervisorJob()
        assertEquals(4, 2 + 2)
    }
}
