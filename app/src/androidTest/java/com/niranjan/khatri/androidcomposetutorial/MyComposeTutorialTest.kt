package com.niranjan.khatri.androidcomposetutorial

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.niranjan.khatri.androidcomposetutorial.basics.MyComposableTestable
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author NIRANJAN KHATRI
 * @since 12/05/24
 * @version 1
 */

@RunWith(AndroidJUnit4::class)
class MyComposableTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun myComposable_isDisplayed() {
        composeTestRule.setContent {
            MyComposableTestable()
        }

        composeTestRule.onNodeWithText("Namaste, Compose!").assertExists()
    }
}
