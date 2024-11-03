package com.niranjan.khatri.androidcomposetutorial.advanced

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun MyComposableExample(items: List<String>) {
    val filteredItems = items.filter { it.startsWith("A") } // Calculation

    LazyColumn {
        items(filteredItems) { item ->
            Text(text = item)
        }
    }
}



@Composable
fun MyComposableImprovised(items: List<String>) {
    val filteredItems = remember(items) { items.filter { it.startsWith("A") } }

    LazyColumn {
        items(filteredItems) { item ->
            Text(text = item)
        }
    }
}