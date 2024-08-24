package com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun NBasicDialog(){
    AlertDialog(
        onDismissRequest = {
            // Dismiss the dialog
        },
        title = {
            Text("Delete Progress")
        },
        text = {
            Text("Are you sure you want to delete your progress? This action cannot be undone.")
        },
        confirmButton = {
            Button(
                onClick = {
                    // Handle progress deletion
                }
            ) {
                Text("Delete")
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    // Dismiss the dialog
                }
            ) {
                Text("Cancel")
            }
        }
    )
}

@Preview
@Composable
fun NBasicDialog_Preview(){
    NBasicDialog()
}

@Composable
fun NFullScreenDialog(){
    Dialog(
        onDismissRequest = { /*TODO*/ },
        properties = DialogProperties(
            usePlatformDefaultWidth = false // This is important!
        )
    ) {
        // Full-screen content goes here
        Surface(modifier = Modifier.fillMaxSize(),
            color = Color.White // Or any background color you want
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("This is a full-screen dialog!")
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { /*TODO*/ }) {
                    Text("Close")
                }
            }
        }
    }
}

@Preview
@Composable
fun NFullScreenDialog_Preview(){
    NFullScreenDialog()
}
