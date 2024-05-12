package com.niranjan.khatri.androidcomposetutorial.basics

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.niranjan.khatri.androidcomposetutorial.R

/**
 * @author NIRANJAN KHATRI
 * @since 12/05/24
 * @version 1
 */

@Composable
fun AccessiblityInComposeExamples() {
    // Adding Content Description to Images
    Image(
        painter = painterResource(id = R.drawable.ic_car),
        contentDescription = "A photo of a cat",
    )
    // Using accessibile text styles
    Text(
        text = "Namaste, compose!",
        style = TextStyle(
            fontSize = 14.sp, // Supporting different text sizes using salable Sp
            fontWeight = FontWeight.Bold,
            color = Color.Black,
        )
    )
    // Using accessible color
    Text(
        text = "Namaste, compose!",
        style = TextStyle(color = Color.White),
        modifier = Modifier.background(Color.Black)
    )
    // Providing touch target sizes
    Button(
        onClick = { /* Handle click */ },
        modifier = Modifier.size(48.dp)
    ){
        Text(text = "Button")
    }
    // Supporting keyboard navigation
    TextField(
        value = "",
        onValueChange = {},
        modifier = Modifier.focusable()
    )
    // Supporting screen readers
    Text(
        text = "Namaste, world!",
        modifier = Modifier.semantics { contentDescription = "Namaste" }
    )
}
