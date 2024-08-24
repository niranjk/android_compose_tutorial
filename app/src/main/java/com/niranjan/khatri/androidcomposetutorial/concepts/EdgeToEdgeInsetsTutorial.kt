package com.niranjan.khatri.androidcomposetutorial.concepts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
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
