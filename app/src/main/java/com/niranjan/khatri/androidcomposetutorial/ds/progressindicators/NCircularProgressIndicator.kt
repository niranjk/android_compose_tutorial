package com.niranjan.khatri.androidcomposetutorial.ds.progressindicators

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.niranjan.khatri.androidcomposetutorial.ds.theme.LocalTypography
import kotlinx.coroutines.delay

@Composable
fun DataLoadingScreen() {
    var isLoading by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isLoading) {
            CircularProgressIndicator()
        } else {
            // Display data once loaded
            Text("Data loaded!", style = LocalTypography.current.headlineLarge)
        }
    }

    // Simulate data loading (replace with actual data fetching)
    LaunchedEffect(Unit) {
        delay(3000) // Simulate loading delay
        isLoading = false
    }
}

@Preview
@Composable
fun DataLoadingScreen_Preview(){
    DataLoadingScreen()
}