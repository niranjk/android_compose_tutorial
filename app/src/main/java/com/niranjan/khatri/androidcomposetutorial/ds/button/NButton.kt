package com.niranjan.khatri.androidcomposetutorial.ds.button

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun NButton() {
    Button(onClick = { /*TODO*/ }) {
        Text(text = "Do something Button")
    }
}

@Preview
@Composable
fun Preview_Button() {
    NButton()
}
