package com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val LocalShapes =
    compositionLocalOf {
        NShapes()
    }

data class NShapes(
    val space: Space = Space(),
    val radius: Radius = Radius(),
    val shadow: Shadow = Shadow(),
)

data class Space(
    val spaceOne: Dp = 1.dp,
    val spaceSmall: Dp = 2.dp,
    val spaceMedium: Dp = 8.dp,
    val spaceLarge: Dp = 16.dp,
    val spaceXLarge: Dp = 32.dp,
)

data class Radius(
    val radiusOne: Dp = 1.dp,
    val radiusSmall: Dp = 2.dp,
    val radiusMedium: Dp = 8.dp,
    val radiusLarge: Dp = 16.dp,
    val radiusXLarge: Dp = 32.dp,
)

data class Shadow(
    val shadowOne: Dp = 1.dp,
    val shadowSmall: Dp = 2.dp,
    val shadowMedium: Dp = 8.dp,
    val shadowLarge: Dp = 16.dp,
    val shadowXLarge: Dp = 32.dp,
)
