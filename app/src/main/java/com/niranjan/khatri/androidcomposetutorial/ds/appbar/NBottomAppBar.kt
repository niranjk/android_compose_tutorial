package com.niranjan.khatri.androidcomposetutorial.ds.appbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.niranjan.khatri.androidcomposetutorial.ds.theme.LocalTypography

@Composable
fun ResponsiveBottomAppBar(screenSize: ScreenSize) {
    BottomAppBar {
        if (screenSize == ScreenSize.Large) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Menu"
                )
            }

            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Favorite"
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.Face,
                    contentDescription = "Face"
                )
            }
            // More action items with icons and labels
            IconButton(onClick = {/*TODO*/ }) {
                Column {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search"
                    )
                    Spacer(Modifier.width(4.dp))
                    Text("ABC")
                }
            }
        } else {
            // Show only essential action items with icons
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Menu"
                )
            }

            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Favorite"
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.Face,
                    contentDescription = "Face"
                )
            }
        }
    }
}


// Define ScreenSize categories
enum class ScreenSize { Small, Medium, Large }

// Composable function to get the current screen size
@Composable
fun rememberWindowSizeClass(): ScreenSize {
    val configuration = LocalConfiguration.current
    return when {
        configuration.screenWidthDp < 600 -> ScreenSize.Small
        configuration.screenWidthDp < 840 -> ScreenSize.Medium
        else -> ScreenSize.Large
    }
}

@Composable
fun MyBottomBarScreen() {
    val screenSize = rememberWindowSizeClass()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { ResponsiveBottomAppBar(screenSize = screenSize) }
    ) { paddingValues ->
        // Your screen content here, using paddingValues to avoid overlap with the Bottom App Bar
        Box(modifier = Modifier.padding(paddingValues).fillMaxSize(),
            contentAlignment = Alignment.Center){
            Text(modifier = Modifier.padding(paddingValues), text = "Bottom Bar Screen", style = LocalTypography.current.headlineLarge)

        }
    }
}

@Preview
@Composable
fun ResponseBottomAppBar_Preview(){
    MyBottomBarScreen()
}