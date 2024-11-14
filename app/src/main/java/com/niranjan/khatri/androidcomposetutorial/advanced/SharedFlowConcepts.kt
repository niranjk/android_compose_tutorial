package com.niranjan.khatri.androidcomposetutorial.advanced

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn

class MySharedViewModel : ViewModel() {
    // Simulate a network API call
    private fun fetchData(): Flow<String> = flow {
        delay(1000) // Simulate network delay
        emit("Data from API")
    }

    // Expose the shared Flow as a StateFlow
    val sharedDataFlow = fetchData().shareIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        replay = 1
    )
}

@Preview
@Composable
fun MySharedScreen(viewModel: MySharedViewModel = MySharedViewModel()) {
    val data by viewModel.sharedDataFlow.collectAsState(initial = "Loading...")
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = data, style = MaterialTheme.typography.headlineLarge)
    }
}