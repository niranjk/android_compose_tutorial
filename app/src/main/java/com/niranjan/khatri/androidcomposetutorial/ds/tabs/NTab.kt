package com.niranjan.khatri.androidcomposetutorial.ds.tabs

import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.niranjan.khatri.androidcomposetutorial.ds.DevicePreview
import com.niranjan.khatri.androidcomposetutorial.ds.models.TextModel
import com.niranjan.khatri.androidcomposetutorial.ds.theme.NAppTheme

typealias OnTabSwitched = ((Int) -> Unit)
@Composable
fun NTab(
    modifier: Modifier = Modifier,
    tabs: List<TextModel>,
    selectedIndex: Int = 0,
    tabSwitched: OnTabSwitched,
) {
    TabRow(
        modifier = modifier,
        selectedTabIndex = selectedIndex,
        indicator = { tabPositions ->
            TabRowDefaults.SecondaryIndicator(
                Modifier.tabIndicatorOffset(tabPositions[selectedIndex]),
                color = Color.Red, // Set your desired indicator color here
            )
        },
    ) {
        tabs.forEachIndexed { index, title ->
            Tab(
                selected = selectedIndex == index,
                onClick = { tabSwitched(index) },
                text = { Text(title.text()) },
            )
        }
    }
}

@Composable
@DevicePreview
fun NTab_Preview() {
    var selectedTabIndex by remember { mutableStateOf(0) }
    NAppTheme {
        NTab( tabs = listOf( TextModel.StringModel("Tab 1"),
                    TextModel.StringModel("Tab 2"),
                    TextModel.StringModel("Tab 3"),
                )) { selectedTabIndex = it }
    }
}
