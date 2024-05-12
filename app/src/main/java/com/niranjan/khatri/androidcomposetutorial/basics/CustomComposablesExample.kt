package com.niranjan.khatri.androidcomposetutorial.basics

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import com.niranjan.khatri.androidcomposetutorial.list.ListItem

/**
 * @author NIRANJAN KHATRI
 * @since 12/05/24
 * @version 1
 */

@Composable
fun CustomComposable() {
    // Implementation of the custom composable created
    Column {
        MyButton(text = "Click me") {
            // Handle button click
        }

        MyTextField(value = "Enter some text") { newValue ->
            // Update the text field value
        }
    }
}

// My Custom Button
@Composable
fun MyButton(
    text: String,
    onClick: () -> Unit,
) {
    Button(onClick = onClick) {
        Text(text = text)
    }
}

// My Custom text field
@Composable
fun MyTextField(
    value: String,
    onValueChange: (String) -> Unit,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
    )
}