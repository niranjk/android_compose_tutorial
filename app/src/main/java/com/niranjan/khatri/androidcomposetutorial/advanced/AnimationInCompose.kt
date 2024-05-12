package com.niranjan.khatri.androidcomposetutorial.advanced

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color

/**
 * @author NIRANJAN KHATRI
 * @since 12/05/24
 * @version 1
 */

/**
 * Animate the color of a composable
 */
@Composable
fun AnimateColorAsStateExample() {
    var color by remember { mutableStateOf(Color.Red) }

    Column {
        Button(onClick = { color = if (color == Color.Red) Color.Blue else Color.Red }) {
            Text(text = "Toggle color")
        }

        Box(modifier = Modifier.background(color = animateColorAsState(color).value))
    }
}

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