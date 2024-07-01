package com.niranjan.khatri.androidcomposetutorial.advanced

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Suppress("ktlint:standard:function-naming", "ktlint:standard:no-consecutive-comments")
/**
 * @author NIRANJAN KHATRI
 * @since 13/05/24
 * @version 1
 */

// State Hoisting
@Composable
fun MyStateHoisting() {
    var count by remember { mutableStateOf(0) }
    Button(onClick = { /*TODO*/ }) {
        Text(text = "Count : $count")
    }
}

// Side Effects

@Suppress("ktlint:standard:function-naming")
// Keys
@Composable
fun MyKeyComposable(items: List<String>) {
    LazyColumn {
        items(items, key = { it }) { item ->
            Text(text = item)
        }
    }
}
