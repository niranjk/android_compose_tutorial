package com.niranjan.khatri.androidcomposetutorial

import androidx.benchmark.junit4.BenchmarkRule
import androidx.benchmark.junit4.measureRepeated
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.selectors.BasicSwitch
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MyComposeBenchmark {

    @get:Rule
    val benchmarkRule = BenchmarkRule()

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun benchmarkMyComposable() {
        benchmarkRule.measureRepeated {
            composeTestRule.setContent {
                BasicSwitch() // The composable function to benchmark
            }
        }
    }
}