package com.niranjan.khatri.androidcomposetutorial.concepts.edgetoedge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class EdgeToEdgeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This app draws behind the system bars, so we want to handle fitting system windows
        enableEdgeToEdge()
        setContent {
            // ... your composables
        }
    }
}

/***
 * <activity
 *     ...
 *     android:windowSoftInputMode="adjustResize">
 *     ...
 * </activity>
 */


@Composable
fun PaddingExampleScreen() {
    Scaffold(
        modifier = Modifier.
        safeContentPadding() // Pads the content within the Scaffold

    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            // Content will be padded according to system bars and other insets
            // ...
        }
    }
}

@Composable
fun SizingExampleScreen() {
    val statusBarHeight = WindowInsets.statusBars.getTop(LocalDensity.current).dp
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(statusBarHeight)
                .background(Color.Red)
        ) // A red box with the height of the status bar
        // ... rest of the content
    }
}


@Preview
@Composable
fun InsetsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding() // Add padding for the status bar
            .navigationBarsPadding() // Add padding for the navigation bar
    ) {
        // Content with padding
        Text(
            "This text has padding for status and navigation bars",
            modifier = Modifier
                .background(Color.LightGray)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Content without padding
        Text(
            "This text has no padding",
            modifier = Modifier
                .background(Color.Yellow)
        )
    }
}

@Composable
fun MyComposable() {
    Box(modifier = Modifier.windowInsetsPadding(WindowInsets.systemBars)) {
        // Content that considers system bars insets
    }
}



@Composable
fun MySystemBarPaddingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding() // Add padding for status and navigation bars
    ) {
        // Your content here will have padding to avoid overlapping system bars
    }
}