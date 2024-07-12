package com.niranjan.khatri.androidcomposetutorial.advanced

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

/**
 * @author NIRANJAN KHATRI
 * @since 12/05/24
 * @version 1
 */

/**
 * Animate the color of a composable
 */
@Preview
@Composable
fun AnimateColorAsStateExample() {
    var color by remember { mutableStateOf(Color.Red) }

    Column {
        Button(onClick = { color = if (color == Color.Red) Color.Blue else Color.Red }) {
            Text(text = "Toggle color")
        }

        Box(modifier = Modifier.background(color = animateColorAsState(color).value).animateBackgroundColorOnClick(
            onClick = {
                color = if (color == Color.Red) Color.Blue else Color.Red
            }
        ))
    }
}

// Create a modifier that animates the background color of a composable when it is clicked
@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.animateBackgroundColorOnClick(
    onClick: () -> Unit,
    backgroundColor: Color = Color.Blue,
    pressedBackgroundColor: Color = Color.Gray
) : Modifier = composed {
    var isPressed by remember { mutableStateOf(false) }

    Modifier
        .clickable(onClick = {
            isPressed = true
            onClick()
            isPressed = false
        })
        .background(
            color = animateColorAsState(
                if (isPressed) pressedBackgroundColor else backgroundColor
            ).value
        )
}

@Preview
@Composable
fun MyComposableAnimation() {
    var count by remember { mutableStateOf(0) }

    Column {
        Button(onClick = { count++ }) {
            Text(text = "Increment count")
        }

        AnimatedContent(
            targetState = count,
            transitionSpec = {
                // Fade out the old count and fade in the new count
                fadeIn() togetherWith fadeOut()
            }, label = "AnimatedContent"
        ) { targetCount ->
            Text(text = "Count: $targetCount")
        }
    }
}