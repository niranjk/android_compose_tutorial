package com.niranjan.khatri.androidcomposetutorial.concepts.animations

import androidx.compose.animation.Animatable
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.niranjan.khatri.androidcomposetutorial.R

@Preview
@Composable
fun TweenAnimationExample() {
    var size by remember { mutableStateOf(100.dp) }
    val animatedSize by animateDpAsState(
        targetValue = size,
        animationSpec = tween(
            durationMillis = 1000, // 1-second duration
            easing = LinearEasing // Optional: Use a different easing curve
        ), label = "Animation Label"
    )

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(animatedSize)
                .background(Color.Blue)
        )

        Button(onClick = { size = if (size == 100.dp) 200.dp else 100.dp }) {
            Text("Toggle Size")
        }
    }
}

@Preview
@Composable
fun SpringAnimationExample() {
    var size by remember { mutableStateOf(100.dp) }
    val animatedSize by animateDpAsState(
        targetValue = size,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ), label = "Spring Animation"
    )

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(animatedSize)
                .background(Color.Red)
        )

        Button(onClick = { size = if (size == 100.dp) 200.dp else 100.dp }) {
            Text("Toggle Size")
        }
    }
}


@Preview
@Composable
fun KeyframesAnimationExample() {
    var size by remember { mutableStateOf(100.dp) }
    val animatedSize by animateDpAsState(
        targetValue = size,
        animationSpec = keyframes {
            durationMillis = 1500 // Total duration of the animation

            // Define values at specific time points
            100.dp at 0 using LinearEasing // Start at 100.dp
            200.dp at 500 using FastOutSlowInEasing // Grow to 200.dp
            150.dp at 1000 using LinearEasing // Shrink to 150.dp
            size at 1500 using FastOutSlowInEasing // End at targetValue
        }, label = "Keyframes Animation"
    )

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(animatedSize)
                .background(Color.Green)
        )

        Button(onClick = { size = if (size == 100.dp) 200.dp else 100.dp }) {
            Text("Toggle Size")
        }
    }
}

@Preview
@Composable
fun RepeatableAnimationExample() {
    var isAnimating by remember { mutableStateOf(false) }
    val animatedColor by animateColorAsState(
        targetValue = if (isAnimating) Color.Yellow else Color.Magenta,
        animationSpec = repeatable(
            iterations = 5, // Repeat 5 times
            animation = tween(durationMillis = 1000), // Each iteration takes 1 second
            repeatMode = RepeatMode.Reverse // Reverse animation on each repeat
        ), label = "Repeatable Animation"
    )

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(animatedColor)
        )

        Button(onClick = { isAnimating = !isAnimating }) {
            Text(if (isAnimating) "Stop" else "Start")
        }
    }
}


@Preview
@Composable
fun InfiniteRepeatableAnimationExample() {
    val infiniteTransition = rememberInfiniteTransition(label = "Infinite Transition")
    val color by infiniteTransition.animateColor(
        initialValue = Color.Blue,
        targetValue = Color.Red,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        ), label = "Infinite Repeatable Animation"
    )

    Box(
        modifier = Modifier
            .size(100.dp)
            .background(color)
    )
}

@Preview
@Composable
fun AnimatedVisibilityExample() {
    var visible by remember { mutableStateOf(true) }
    Column {
        Button(onClick = { visible = !visible }) {
            Text(if (visible) "Hide" else "Show")
        }
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(animationSpec = tween(1000)),
            exit = fadeOut(animationSpec = tween(1000))
        ) {
            Text("Hello, this text is animated!")
        }
    }
}

@Preview
@Composable
fun AnimatedContentExample() {
    var count by remember { mutableIntStateOf(0) }
    Column {
        Button(onClick = { count++ }) {
            Text("Increase")
        }
        AnimatedContent(
            targetState = count,
            transitionSpec = {
                if (targetState > initialState) {
                    (slideInVertically { height -> height } + fadeIn()).togetherWith(
                        slideOutVertically { height -> -height } + fadeOut())
                } else {
                    (slideInVertically { height -> -height } + fadeIn()).togetherWith(
                        slideOutVertically { height -> height } + fadeOut())
                }.using(
                    sizeTransform = SizeTransform(clip = false)
                )
            }, label = "Animated Content"
        ) { targetCount ->
            Text("Count: $targetCount")
        }
    }
}


@Preview
@Composable
fun AnimatableExample() {
    var targetValue by remember { mutableFloatStateOf(0f) }
    // Coroutine-based animations in Compose offer a powerful way to create smooth and customizable animations.
    val color = remember { Animatable(Color.Red) }
    LaunchedEffect(targetValue) {
        color.animateTo(
            targetValue = if (targetValue > 0.5f) Color.Green else Color.Red,
            animationSpec = tween(durationMillis = 500)
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(color.value)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Slider(
            value = targetValue,
            onValueChange = { targetValue = it },
            valueRange = 0f..1f
        )
    }
}
