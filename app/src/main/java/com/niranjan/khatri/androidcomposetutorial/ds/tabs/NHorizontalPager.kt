package com.niranjan.khatri.androidcomposetutorial.ds.tabs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.niranjan.khatri.androidcomposetutorial.ds.DevicePreview
import com.niranjan.khatri.androidcomposetutorial.ds.models.TextModel
import com.niranjan.khatri.androidcomposetutorial.ds.theme.NAppTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NHorizontalPager() {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabItemsList = listOf(TextModel.StringModel("Home"), TextModel.StringModel("Profile"), TextModel.StringModel("Settings"))
    val pagerState =
        rememberPagerState {
            tabItemsList.size
        }

    // Synchronize tab selection and Pager State
    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }
    LaunchedEffect(pagerState.currentPage) {
        selectedTabIndex = pagerState.currentPage
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        NTab(selectedIndex = selectedTabIndex, tabs = tabItemsList) {
            selectedTabIndex = it
        }
        HorizontalPager(state = pagerState, modifier = Modifier.fillMaxWidth(), userScrollEnabled = false) { index ->
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                // Display the content for each tab based on the page or index
                Text(text = tabItemsList[index].text)
            }
        }
    }
}

@DevicePreview
@Composable
fun NHorizontalPager_Preview()  {
    NAppTheme {
        NHorizontalPager()
    }
}
