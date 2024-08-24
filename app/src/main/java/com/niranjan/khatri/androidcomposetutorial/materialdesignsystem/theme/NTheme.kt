package com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun NAppTheme(
    lightScheme: NColor = lightColorScheme(),
    darkScheme: NColor = darkColorScheme(),
    shapes: NShapes = LocalShapes.current,
    typography: NTypography = LocalTypography.current,
    content: @Composable () -> Unit,
) {
    val colorScheme =
        if (isSystemInDarkTheme()) {
            darkScheme
        } else {
            lightScheme
        }
    CompositionLocalProvider(
        LocalColorScheme provides colorScheme,
        LocalShapes provides shapes,
        LocalTypography provides typography,
    ) {
        MaterialTheme(
            content = content,
        )
    }
}
