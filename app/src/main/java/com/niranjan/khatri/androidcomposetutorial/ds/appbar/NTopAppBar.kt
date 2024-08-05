package com.niranjan.khatri.androidcomposetutorial.ds.appbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.niranjan.khatri.androidcomposetutorial.ds.theme.LocalTypography


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResponsiveTopAppBar(
    screenSize: ScreenSize,
    title: String,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {}
) {
    when (screenSize) {
        ScreenSize.Small -> {
            TopAppBar(
                title = { Text(title) },
                navigationIcon = navigationIcon, actions = actions
            )
        }

        ScreenSize.Medium -> {
            MediumTopAppBar(
                title = { Text(title) },
                navigationIcon = navigationIcon,
                actions = actions
            )
        }

        ScreenSize.Large -> {
            LargeTopAppBar(
                title = {
                    Text(
                        title,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = navigationIcon,
                actions = actions
            )
        }
    }
}

@Composable
fun MyTopAppBarScreen() {
    val screenSize = rememberWindowSizeClass()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { ResponsiveTopAppBar(screenSize = screenSize,
            title = "MyScreen Title",
            navigationIcon = {
                IconButton(onClick = { /* Handle navigation */ }) {
                    Icon(Icons.Filled.Menu, contentDescription = "Menu")
                }
            },
            actions = {
                IconButton(onClick = { /* Handle action 1 */ }) {
                    Icon(Icons.Filled.Search, contentDescription = "Search")
                }
                // Add more actions here if needed
            }) },
        bottomBar = {}
    ) { paddingValues ->
        // Your screen content here, using paddingValues to avoid overlap with the Bottom App Bar
        Box(modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
            contentAlignment = Alignment.Center){
            Text(modifier = Modifier.padding(paddingValues), text = "Top Bar Screen", style = LocalTypography.current.headlineLarge)
        }
    }
}

@Preview
@Composable
fun ResponseTopAppBar_Preview(){
    MyTopAppBarScreen()
}