package com.niranjan.khatri.androidcomposetutorial.ds.snackbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SnackbarExample() {
    var snackbarMessage by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { data ->
                Snackbar(
                    actionColor = MaterialTheme.colorScheme.primary,
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    snackbarData = data,
                    modifier = Modifier.semantics {
                        liveRegion = LiveRegionMode.Polite
                    }
                )
            }
        },
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Snackbar Example") }
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = {
                    snackbarMessage = "This is a Snackbar!"
                    scope.launch {
                        val snackbarResult = snackbarHostState.showSnackbar(
                            message = snackbarMessage,
                            withDismissAction = true, // This adds a dismiss action
                            actionLabel = "Undo"
                        )
                        if (snackbarResult == SnackbarResult.ActionPerformed) {
                            // Action label was clicked
                            // Perform your action here
                        } else if (snackbarResult == SnackbarResult.Dismissed) {
                            // Snackbar was dismissed
                        }
                    }
                }) {
                    Text("Show Snackbar")
                }
            }
        }
    )
}

@Preview
@Composable
fun SnackbarExample_Preview() {
    SnackbarExample()
}