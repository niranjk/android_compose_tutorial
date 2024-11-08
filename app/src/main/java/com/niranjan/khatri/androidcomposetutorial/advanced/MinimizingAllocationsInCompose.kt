package com.niranjan.khatri.androidcomposetutorial.advanced

import androidx.activity.result.launch
import androidx.collection.ScatterMap
import androidx.collection.mutableScatterMapOf
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.IntSize
import kotlinx.coroutines.launch

// 1. Using Value Classes
// Instead of:
val width = Integer(100)
val height = Integer(50)

// Use:
val size = IntSize(width = 100, height = 50)


// 2.Custom Collections
@Composable
fun CustomCollectionExample() {
    // Instead of:
    val data1 = hashMapOf<String, Int>()
    // Use:
    val data2 = mutableScatterMapOf<String, Int>()
}

// 3. Avoid Unnecessary Object Creation
@Composable
fun MyList(items: List<String>) {
    LazyColumn {
        items(items) { item ->
            // Avoid creating a new Text composable on every recomposition
            val text = remember { mutableStateOf(item) }
            Text(text = text.value)
        }
    }
}

@Composable
fun MyButton() {
    var isLoading by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope() // Create a coroutine scope

    Button(onClick = {
        isLoading = true
        coroutineScope.launch { // Launch coroutine within the scope
            // Perform asynchronous task
            isLoading = false
        }
    }) {
        Text(if (isLoading) "Loading..." else "Click Me")
    }
}