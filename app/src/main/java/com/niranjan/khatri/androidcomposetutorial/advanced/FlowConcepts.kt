package com.niranjan.khatri.androidcomposetutorial.advanced

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

val numbersFlow = flow {
    for (i in 1..5) {
        emit(i)
        delay(100) // Simulate some delay
    }
}

val namesFlow = flowOf("Alice", "Bob", "Charlie")

val listFlow = listOf(1, 2, 3).asFlow()


@Composable
fun MyComposableFunction() {
    // Create a simple Flow that emits numbers 1 to 5 with a delay
    val numbersFlow = remember {
        flow {
            for (i in 1..5) {
                emit(i)
                delay(100) // Simulate some delay
            }
        }
    }

    // Collect the Flow using LaunchedEffect
    LaunchedEffect(key1 = numbersFlow) {
        numbersFlow.collect { number ->
            // Process the emitted number
            Log.d("Flow", "Received: $number")
        }
    }

}

