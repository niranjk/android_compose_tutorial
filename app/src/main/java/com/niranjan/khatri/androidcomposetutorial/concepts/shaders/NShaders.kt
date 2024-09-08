package com.niranjan.khatri.androidcomposetutorial.concepts.shaders

import android.graphics.RuntimeShader
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.withInfiniteAnimationFrameMillis
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.tooling.preview.Preview
import org.intellij.lang.annotations.Language

@Language("agsl")
val shader = """
  uniform float2 resolution;
  half4 main(float2 fragCoord) {
    // Your shader code here
  }
"""

@Preview
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun RedSquareShader() {
    @Language("agsl")
    val shader = """
        half4 main(float2 fragCoord) {
          return half4(1.0, 0.0, 0.0, 1.0); 
        }
    """

    val runtimeShader = remember(shader) { RuntimeShader(shader) }
    val shaderBrush = remember(runtimeShader) { ShaderBrush(runtimeShader) }

    Canvas(modifier = Modifier.fillMaxSize().drawWithCache {
        onDrawBehind {
            drawRect(brush = shaderBrush)
        }
    }){}
}

@Preview
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun GradientShader(color1: Color = Color.Red, color2: Color = Color.Yellow) {
    @Language("agsl")
    val shader = """
        uniform float2 resolution;
        uniform half4 color1;
        uniform half4 color2;

        half4 main(float2 fragCoord) {
          float2 uv = fragCoord/resolution;
          float fraction = distance(uv, float2(0.0, 1.0));
          return mix(color2, color1, fraction);
        }
    """

    val runtimeShader = remember(shader) { RuntimeShader(shader) }
    val shaderBrush = remember(runtimeShader) { ShaderBrush(runtimeShader) }

    Canvas(modifier = Modifier.fillMaxSize().drawWithCache {
        onDrawBehind {
            val width = size.width
            val height = size.height
            runtimeShader.setFloatUniform("resolution", width, height)
            runtimeShader.setFloatUniform("color1", color1.red, color1.green, color1.blue, color1.alpha)
            runtimeShader.setFloatUniform("color2", color2.red, color2.green, color2.blue, color2.alpha)
            drawRect(brush = shaderBrush)
        }
    }){

    }
}

@Preview
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun AnimatedShader(color1: Color = Color.Red, color2: Color = Color.Yellow) {
    var time by remember { mutableStateOf(0f) }

    @Language("agsl")
    val shader = """
        uniform float2 resolution;
        uniform half4 color1;
        uniform half4 color2;
        uniform float time;

        half4 main(float2 fragCoord) {
          float2 uv = fragCoord/resolution;
          float fraction = sin(time*0.5 + distance(uv, float2(0.0, 1.0))*10.0) * 0.5 + 0.5;return mix(color1, color2, fraction);
        }
    """

    val runtimeShader = remember(shader) { RuntimeShader(shader) }
    val shaderBrush = remember(runtimeShader) { ShaderBrush(runtimeShader) }

    Canvas(modifier = Modifier.fillMaxSize().drawWithCache {
        onDrawBehind {
            val width = size.width
            val height = size.height
            runtimeShader.setFloatUniform("resolution", width, height)
            runtimeShader.setFloatUniform("color1", color1.red, color1.green, color1.blue, color1.alpha)
            runtimeShader.setFloatUniform("color2", color2.red, color2.green, color2.blue, color2.alpha)
            runtimeShader.setFloatUniform("time", time)
            drawRect(brush = shaderBrush)
        }
    }){

    }

    LaunchedEffect(Unit) {
        withInfiniteAnimationFrameMillis { frameTime ->
            time = frameTime / 1000f
        }
    }
}

fun Modifier.noisyShader(shader: RuntimeShader, time: Float): Modifier = this.then(
    object : DrawModifier {
        @RequiresApi(Build.VERSION_CODES.TIRAMISU)
        override fun ContentDrawScope.draw() {
            shader.setFloatUniform("time", time)
            drawContent()
            val shaderBrush = ShaderBrush(shader)
            drawRect(brush = shaderBrush, blendMode = BlendMode.SrcAtop)
        }
    }
)

@Preview
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun NoisyText() {
    var time by remember { mutableStateOf(0f) }

    @Language("agsl")
    val shader = """
        uniform float time;

        half4 main(float2 fragCoord) {
          float2 uv = fragCoord / float2(1000.0, 1000.0); // Example resolution
          float noise = fract(sin(dot(uv, float2(12.9898,78.233))) * 43758.5453);
          float2 offset = float2(noise - 0.5, noise - 0.5) * 0.01 * sin(time);
          half4 color = half4(1.0, 1.0, 1.0, noise); // Example color with noise
          return color;
        }
    """

    val runtimeShader = remember(shader) { if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        RuntimeShader(shader)
    } else {
        TODO("VERSION.SDK_INT < TIRAMISU")
    }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .noisyShader(runtimeShader, time)
    ) {
        Text("Hello")
        Button(onClick = { /*TODO*/ }) {
            Text("Click me")
        }
    }

    LaunchedEffect(Unit) {
        withInfiniteAnimationFrameMillis { frameTime ->
            time = frameTime / 1000f
        }
    }
}