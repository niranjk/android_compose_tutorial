package com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.textfields

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun OutlinedTextFieldExample() {
    var text by remember { mutableStateOf("") }
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Outlined Textfield Label") },
            placeholder = { Text("Outlined Textfield Placeholder") }
        )
    }
}

@Preview
@Composable
fun OutlinedTextField_Preview(){
    OutlinedTextFieldExample()
}


@Composable
fun FilledTextFieldExample() {
    var text by remember { mutableStateOf("") }
    Box(modifier = Modifier.fillMaxSize().padding(16.dp),
        contentAlignment = Alignment.Center) {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Label") },
            placeholder = { Text("Placeholder") },
            leadingIcon = { Icon(Icons.Filled.Email, contentDescription = "Email") },
            supportingText = { Text("Supporting text") },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color.LightGray,
                focusedContainerColor = Color.LightGray
            )
        )
    }
}

@Preview
@Composable
fun FilledTextField_Preview(){
    FilledTextFieldExample()
}