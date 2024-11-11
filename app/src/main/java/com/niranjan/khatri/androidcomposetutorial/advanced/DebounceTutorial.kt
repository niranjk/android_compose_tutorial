package com.niranjan.khatri.androidcomposetutorial.advanced

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


@Composable
fun MyDebounceScreen() {
    var searchQuery by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() } // Create SnackbarHostState

    Column(modifier = Modifier.padding(16.dp).fillMaxWidth().fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        TextField(
            value = searchQuery,
            onValueChange = { newQuery ->
                searchQuery = newQuery
            },
            label = { Text("Search") }
        )

        // Display the debounced search query
        Text("Debounced Query: $searchQuery")
    }

    LaunchedEffect(key1 = searchQuery) {
        snapshotFlow { searchQuery }
            .debounce(timeoutMillis = 1000, coroutineScope = this) { query ->
                // Perform search with the debounced query
                println("Searching for: $query")
                // Show Snackbar message
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(
                        message = "Searching for: $query",
                        duration = SnackbarDuration.Short
                    )
                }
            }
    }
    // Add SnackbarHost to your composable hierarchy
    SnackbarHost(hostState = snackbarHostState)
}

// Debounce extension function
@OptIn(FlowPreview::class)
fun <T> Flow<T>.debounce(
    timeoutMillis: Long = 1000L, // Default timeout of 1 second
    coroutineScope: CoroutineScope,
    destinationFunction: suspend (T) -> Unit
) {
    onEach {
        coroutineScope.launch {
            snapshotFlow { it }
                .debounce(timeoutMillis)
                .distinctUntilChanged()
                .filter { it != null && it.toString().isNotBlank() }
                .mapLatest {
                    destinationFunction(it)
                }
                .collect {} // Use collect {} without a collector
        }
    }.launchIn(coroutineScope)
}
