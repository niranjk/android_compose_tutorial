package com.niranjan.khatri.androidcomposetutorial.concepts.brush

import android.graphics.BitmapFactory
import android.graphics.Shader
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.niranjan.khatri.androidcomposetutorial.R

@Composable
fun BrushExample() {
    // Solid Color
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(
                SolidColor(Color.Red)
            )
    )
}

@Preview
@Composable
fun SolidColorPreivew() {
    BrushExample()
}

@Composable
fun GradientExamples() {
    Column {// Linear Gradient
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(
                    Brush.linearGradient(
                        colors = listOf(Color.Red, Color.Yellow, Color.Green)
                    )
                )
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Radial Gradient
        Box(
            modifier = Modifier
                .size(200.dp)
                .background(
                    Brush.radialGradient(
                        colors = listOf(Color.Blue, Color.White), center = Offset(100f, 100f),
                        radius = 100f
                    )
                )
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Sweep Gradient
        Box(
            modifier = Modifier
                .size(200.dp)
                .background(
                    Brush.sweepGradient(
                        colors = listOf(Color.Red, Color.Yellow, Color.Green),
                        center = Offset(100f, 100f)
                    )
                )
        )
    }
}

@Preview
@Composable
fun GradientPreview() {
    GradientExamples()
}

@Composable
fun ColorStopExample() {
    // Color Stops Example with start and end points
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(
                Brush.linearGradient(
                    colors = listOf(Color.Red, Color.Yellow),
                    // Simulating a stop at 0.2 for yellow
                    end = Offset(0f, 20f)
                ) // <----
            )
            .background(
                Brush.linearGradient(
                    colors = listOf(Color.Yellow, Color.Green),
                    // Starting from where yellow ended
                    start = Offset(0f, 20f) // <----
                )
            )
    )
}

@Preview
@Composable
fun ColorStopPreview() {
    ColorStopExample()
}

@Composable
fun TilingGradientExample() {
    val gradientBrush = Brush.linearGradient(
        colors = listOf(Color.Red, Color.Yellow),
        tileMode = TileMode.Mirror
    )

    Box(
        modifier = Modifier
            .size(200.dp)
            .background(gradientBrush)
    )
}

@Preview
@Composable
fun TilingGradient_Preview() {
    TilingGradientExample()
}

@Composable
fun ImageBrushExample() {
    val context = LocalContext.current
    val bitmap = BitmapFactory.decodeResource(context.resources, R.mipmap.read)
    val imageBitmap = bitmap?.asImageBitmap()

    if (imageBitmap != null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .drawWithContent {
                    drawIntoCanvas { canvas ->
                        val paint = android.graphics.Paint()
                        paint.shader = android.graphics.BitmapShader(
                            imageBitmap.asAndroidBitmap(),
                            Shader.TileMode.REPEAT,
                            Shader.TileMode.REPEAT
                        )
                        canvas.nativeCanvas.drawRect(
                            0f,
                            0f,
                            size.width,
                            size.height,
                            paint
                        )
                    }
                }
        )
    } else {
        // Handle the case where the image could not be loaded
        Text("Image could not be loaded")
    }
}

@Preview
@Composable
fun ImageBrush_Preview() {
    ImageBrushExample()
}


@Composable
fun AnimatedBrushExample() {
    val infiniteTransition = rememberInfiniteTransition(label = "Infinite")
    val offset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(50000),
            repeatMode = RepeatMode.Reverse
        ), label = "Offset"
    )
    Box(
        modifier = Modifier
            .size(200.dp)
            .animatedBackground(
                brush = Brush.linearGradient(
                    colors = listOf(Color.Red, Color.Yellow),
                    start = Offset(offset, 0f),
                    end = Offset(offset + 100f, 0f)
                )
            )
    )
}

fun Modifier.animatedBackground(brush: Brush): Modifier = composed {
    var lastSize by remember { mutableStateOf(IntSize.Zero) }
    val drawModifier = drawBehind {
        if (lastSize != IntSize(size.width.toInt(), size.height.toInt())) {
            lastSize = IntSize(size.width.toInt(), size.height.toInt())
        }
        drawRect(brush = brush)
    }
    Modifier.then(drawModifier)
}

@Preview
@Composable
fun AnimatedBrush_Preview() {
    AnimatedBrushExample()
}