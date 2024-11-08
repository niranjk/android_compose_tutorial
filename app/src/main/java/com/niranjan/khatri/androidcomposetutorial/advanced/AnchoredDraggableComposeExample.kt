package com.niranjan.khatri.androidcomposetutorial.advanced

import androidx.compose.animation.core.tween
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.math.roundToInt


class CustomAnchoredDraggableState(initialValue: Float) {
    var currentValue by mutableStateOf(initialValue)
    var targetValue by mutableStateOf(initialValue)

    fun updateAnchors(anchors: Map<Float, Any>) {
        // Update anchors logic here (not used in this example)
    }

    suspend fun animateTo(targetValue: Float) {
        // Animation logic here (not used in this example)
    }

    fun requireOffset(): Float {
        return currentValue
    }
}

@Preview
@Composable
fun SwipeableMessage() {
    val state = remember { CustomAnchoredDraggableState(0f) }
    val anchors = mapOf<Float, Any>(
        0f to 0f, // Resting state
        with(LocalDensity.current) { 48.dp.toPx() } to with(LocalDensity.current) { 48.dp.toPx() } // Replying state
    )
    var isDragged by remember { mutableStateOf(false) }

    LaunchedEffect(state.targetValue) {
        if (state.targetValue != 0f && !isDragged) {
            delay(300)
            state.currentValue = 0f
            state.targetValue = 0f
        }
    }

    Box(
        modifier = Modifier
            .pointerInput(Unit) {
                detectHorizontalDragGestures(
                    onDragStart = {
                        isDragged = true
                    },
                    onDragEnd = {
                        isDragged = false
                        state.targetValue =
                            if (state.currentValue > anchors.keys.last() / 2) anchors.keys.last() else 0f
                        state.currentValue = state.targetValue
                    },
                    onHorizontalDrag = { change, dragAmount ->
                        change.consume()
                        val newValue =
                            (state.currentValue + dragAmount).coerceIn(0f, anchors.keys.last())
                        state.currentValue = newValue
                    }
                )
            }
            .offset {
                IntOffset(
                    state
                        .requireOffset()
                        .roundToInt(), 0
                )
            },
        contentAlignment = Alignment.Center
    ) {
        Text("Swipe me to reply!")
    }
}

enum class SlideToActionAnchors {
    Start,
    End
}

@Preview
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SwipeToRevealActions() {
    val decayAnimationSpec = rememberSplineBasedDecay<Float>()
    val dragState = remember {
        AnchoredDraggableState(
            initialValue = SlideToActionAnchors.End,
            anchors = DraggableAnchors {
                SlideToActionAnchors.Start at 0f
                SlideToActionAnchors.End at 500f
            },
            positionalThreshold = { d -> d * 0.9f },
            velocityThreshold = { Float.POSITIVE_INFINITY },
            snapAnimationSpec = tween(),
            decayAnimationSpec = decayAnimationSpec
        )
    }

    Box(
        Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .anchoredDraggable(
                state = dragState,
                orientation = Orientation.Horizontal,
                enabled = true
            ),
    ) {
        // Background actions (revealed on swipe)
        Box(
            Modifier
                .fillMaxWidth()
                .background(Color.Red)
                .matchParentSize()
        ) {
            Text(
                "Delete",
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterStart)
            )
        }

        // Main content
        Box(
            Modifier
                .offset { IntOffset(dragState.requireOffset().roundToInt(), 0) }
                .background(Color.Yellow)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text("Swipe to reveal actions")
        }
    }
}