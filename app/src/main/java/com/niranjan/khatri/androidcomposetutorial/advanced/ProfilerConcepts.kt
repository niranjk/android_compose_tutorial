package com.niranjan.khatri.androidcomposetutorial.advanced

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun HeavyCalculationComposable() {
    var result by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        result = performHeavyCalculation()
    }

    Text("Result: $result")
}

fun performHeavyCalculation(): Int {
    // Simulate a CPU-intensive operation
    var sum = 0
    for (i in 1..1000000) {
        sum += i
    }
    return sum
}

data class Data(val originalData: String) {
    fun transformData(): String {
        // Perform potentially expensive operation here, e.g., network request, complex calculation
        // This is just an example, replace with your actual logic
        return originalData.uppercase()
    }
}

@Composable
fun MyComposable(data: Data) {
    val transformedData = remember(data) {
        data.transformData() // Potentially expensive operation
    }
    Text(text = transformedData)
}

// Improvised
@Composable
fun MyComposableOptimized(data: Data) {
    val transformedData by remember(data) {
        derivedStateOf { data.transformData() } // Memoized result
    }
    Text(text = transformedData)
}
