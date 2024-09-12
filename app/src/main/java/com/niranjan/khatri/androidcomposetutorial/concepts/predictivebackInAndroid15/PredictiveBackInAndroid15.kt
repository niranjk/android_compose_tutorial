package com.niranjan.khatri.androidcomposetutorial.concepts.predictivebackInAndroid15

import android.app.Activity
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext


@Composable
fun MyFirstPredictiveBackScreen() {
    var showDialog by remember { mutableStateOf(false) }
    // Use only if it is not first screen of activity.
    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    val activity = LocalContext.current as Activity // Get the current activity

    BackPressHandler(showDialog) {
        showDialog = true
    }

    if (showDialog) {
        ConfirmExitDialog(
            onConfirm = {
                showDialog = false
                /**
                 * In the case of the first screen in an activity,
                 * simply calling onBackPressedDispatcher?.onBackPressed()
                 * would indeed cause the dialog to show again,
                 * leading to an infinite loop.
                 * onBackPressedDispatcher?.onBackPressed()
                */
                activity.finish() // Finish the activity to exit the app if is the first screen
            },
            onDismiss = { showDialog = false }
        )
    }

    Column (
        modifier = Modifier.windowInsetsPadding(WindowInsets.systemBars)
    ){
        Text(text = "My First Screen Content")
    }

}

@Composable
fun MyPredictiveBackScreen() {
    var showDialog by remember { mutableStateOf(false) }
    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    BackPressHandler(showDialog) {
        showDialog = true
    }

    if (showDialog) {
        ConfirmExitDialog(
            onConfirm = {
                showDialog = false
                onBackPressedDispatcher?.onBackPressed()
            },
            onDismiss = { showDialog = false }
        )
    }

    Column (
        modifier = Modifier.windowInsetsPadding(WindowInsets.systemBars)
    ){
        Text(text = "My Screen Content")
    }
}

@Composable
fun BackPressHandler(showDialog: Boolean, onBack: () -> Unit) {
    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner, onBackPressedDispatcher) {
        val callback = object : androidx.activity.OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (!showDialog) {
                    onBack()
                }
            }
        }
        onBackPressedDispatcher?.addCallback(lifecycleOwner, callback)

        onDispose {
            callback.remove()
        }
    }
}

@Composable
fun ConfirmExitDialog(onConfirm: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Confirm Exit") },
        text = { Text("Are you sure you want to exit?") },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text("Yes")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("No")
            }
        }
    )
}