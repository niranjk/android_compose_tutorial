package com.niranjan.khatri.androidcomposetutorial.advanced

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView

/**
 * @author NIRANJAN KHATRI
 * @since 12/05/24
 * @version 1
 */

/**
 * Here we are using Compose view to create a UI in Android View
 */
class MyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create a Compose view
        val composeView = ComposeView(this)
        composeView.setContent {
            Text(text = "Hello, Compose!")
        }

        // Add the Compose view to the layout
        setContentView(composeView)
    }
}

/**
 * Here we are using Android View to create a UI in Compose
 */
@Composable
fun MyComposable() {
    // Create an Android View
    val textView = TextView(LocalContext.current)
    textView.text = "Namaste, Android!"

    // Use the Android View in the composable
    AndroidView(
        factory = { textView },
        modifier = Modifier.fillMaxSize()
    )
}
