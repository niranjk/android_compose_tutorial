package com.niranjan.khatri.androidcomposetutorial.advanced

import android.content.Context
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect

@Composable
fun LoggingSideEffectExample(count: Int) {
    SideEffect {
        println("Count has changed: $count")
    }
    Text("Count: $count")
}

@Composable
fun SharedPreferenceSideEffect(isLoggedIn: Boolean, context: Context) {
    SideEffect {
        val sharedPrefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        sharedPrefs.edit().putBoolean("isLoggedIn", isLoggedIn).apply()
    }

    // ... rest of your composable ...
}