package com.niranjan.khatri.androidcomposetutorial.ui.demo

import androidx.compose.runtime.Composable
import com.niranjan.khatri.androidcomposetutorial.ds.models.TextModel
import com.niranjan.khatri.androidcomposetutorial.ds.tabs.NTab

typealias DemoType = @Composable (() -> Unit)

@Composable
fun DemoShowScreen(type: String) {
    val list: List<DemoType> =
        when (type) {
            "Tab" ->
                listOf {
                    NTab(tabs = listOf(TextModel.StringModel("Home"), TextModel.StringModel("Profile"))) {
                    }
                }
            else ->
                listOf {
                }
        }
}
