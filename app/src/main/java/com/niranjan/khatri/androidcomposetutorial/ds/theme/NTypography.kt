package com.niranjan.khatri.androidcomposetutorial.ds.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val nFontFamily = FontFamily.Serif

val LocalTypography =
    compositionLocalOf {
        NTypography()
    }

data class NTypography(
    val displayLarge: TextStyle =
        TextStyle.Default.copy(
            fontFamily = nFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 32.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.1.sp,
        ),
    val displayMedium: TextStyle =
        TextStyle.Default.copy(
            fontFamily = nFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 22.sp,
            lineHeight = 22.sp,
            letterSpacing = 0.1.sp,
        ),
    val displaySmall: TextStyle =
        TextStyle.Default.copy(
            fontFamily = nFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp,
        ),
    val headlineLarge: TextStyle =
        TextStyle.Default.copy(
            fontFamily = nFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 32.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp,
        ),
    val headlineMedium: TextStyle =
        TextStyle.Default.copy(
            fontFamily = nFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 24.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp,
        ),
    val headlineSmall: TextStyle =
        TextStyle.Default.copy(
            fontFamily = nFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp,
        ),
    val titleLarge: TextStyle =
        TextStyle.Default.copy(
            fontFamily = nFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 24.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp,
        ),
    val titleMedium: TextStyle =
        TextStyle.Default.copy(
            fontFamily = nFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp,
        ),
    val titleSmall: TextStyle =
        TextStyle.Default.copy(
            fontFamily = nFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp,
        ),
    val bodyLarge: TextStyle =
        TextStyle.Default.copy(
            fontFamily = nFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 30.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp,
        ),
    val bodyMedium: TextStyle =
        TextStyle.Default.copy(
            fontFamily = nFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp,
        ),
    val bodySmall: TextStyle =
        TextStyle.Default.copy(
            fontFamily = nFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 10.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp,
        ),
    val labelLarge: TextStyle =
        TextStyle.Default.copy(
            fontFamily = nFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp,
        ),
    val labelMedium: TextStyle =
        TextStyle.Default.copy(
            fontFamily = nFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 42.sp,
            lineHeight = 12.sp,
            letterSpacing = 0.1.sp,
        ),
    val labelSmall: TextStyle =
        TextStyle.Default.copy(
            fontFamily = nFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 8.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp,
        ),
)
