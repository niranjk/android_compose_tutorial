package com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.fab

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun NExtendedFab()  {
    FloatingActionButton(
        onClick = {},
        containerColor = Color.White,
    ) {
        Icon(Icons.Filled.Face, contentDescription = "FAB")
    }
}

@Preview
@Composable
fun Preview_Button()  {
    NExtendedFab()
}
