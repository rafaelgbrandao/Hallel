package com.hallel.core.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule
import org.koin.test.AutoCloseKoinTest

open class BaseTest: AutoCloseKoinTest() {

    @get:Rule
    val instantRule = InstantTaskExecutorRule()

}