package com.niranjan.khatri.androidcomposetutorial.advanced

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun RememberCoroutineScopeExample() {
    var counter by remember { mutableIntStateOf(0) }
    val scope = rememberCoroutineScope()

    Column {
        Text("Counter: $counter")
        Button(onClick = {
            scope.launch {
                delay(1000) // Simulate a delay
                counter++
            }
        }) {
            Text("Increment Counter")
        }
    }
}