package com.niranjan.khatri.androidcomposetutorial.ds.progressindicators

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ImageUploadScreen() {
    var uploadProgress by remember { mutableStateOf(0.0f) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LinearProgressIndicator(progress = uploadProgress)

        Spacer(modifier = Modifier.height(16.dp))

        // Button to trigger the upload and update uploadProgress
        Button(onClick = {
            // Start upload and update progress */
            // This is a simplified example, replace with actual upload logic
            uploadProgress += 0.1f
            }) {
            Text("Upload Image")
        }
    }
}

@Preview
@Composable
fun ImageUploadScreen_Preview(){
    ImageUploadScreen()
}