package com.niranjan.khatri.androidcomposetutorial.ui.demo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.divider.NHorizontalDivider
import com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.divider.NVerticalDivider
import com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.models.TextModel
import com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.selectors.NSwitchLabel
import com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.tabs.NTab
import com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.tag.NTag
import com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.tag.NTagStatus
import com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.theme.LocalColorScheme
import com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.theme.LocalShapes

typealias DemoType = @Composable (() -> Unit)

@Composable
fun DemoShowScreen(type: String) {
    // Switch
    var switchEnabledOff by remember {
        mutableStateOf(false)
    }
    var switchEnabledOn by remember {
        mutableStateOf(true)
    }

    val list: List<DemoType> =
        when (type) {
            "Divider" -> {
                listOf {
                    NHorizontalDivider(thickness = 8.dp, color = LocalColorScheme.current.primary.onPrimary)
                    NVerticalDivider(
                        modifier =
                            Modifier
                                .size(200.dp)
                                .fillMaxWidth(),
                        thickness = LocalShapes.current.space.spaceMedium,
                        color = LocalColorScheme.current.primary.onPrimary,
                    )
                }
            }
            "Switch" ->
                listOf {
                    NSwitchLabel(state = switchEnabledOn, label = "MY Switch", onSwitched = { switchEnabledOn = it })
                    NSwitchLabel(state = switchEnabledOff, onSwitched = { switchEnabledOff = it })
                    NSwitchLabel(state = false, enabled = false, label = "My Disabled Switch", onSwitched = {})
                    NSwitchLabel(state = true, enabled = false, onSwitched = {})
                }
            "Tab" ->
                listOf {
                    NTab(tabs = listOf(TextModel.StringModel("Home"), TextModel.StringModel("Profile"))) {
                    }
                }
            "Tag" ->
                listOf {
                    NTag(text = "Boy", tagModel = NTagStatus.Boy())
                    NHorizontalDivider(thickness = LocalShapes.current.space.spaceMedium)
                    NTag(text = "Girl", tagModel = NTagStatus.Girl())
                    NHorizontalDivider(thickness = LocalShapes.current.space.spaceMedium)
                    NTag(text = "Default", tagModel = NTagStatus.Default())
                }
            else ->
                listOf {
                }
        }

    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .background(LocalColorScheme.current.primary.primaryContainer)
                .padding(
                    LocalShapes.current.space.spaceMedium,
                ),
    ) {
        LazyColumn {
            items(list) {
                it.invoke()
            }
        }
    }
}
