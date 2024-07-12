package com.niranjan.khatri.androidcomposetutorial.ds.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class NColor(
    val primary: Primary,
    val secondary: Secondary,
    val tertary: Tertary,
    val neutral: Neutral,
    val other: Other,
) {
    data class Primary(
        val primary: Color,
        val onPrimary: Color,
        val primaryContainer: Color,
        val onPrimaryContainer: Color,
        val inversePrimary: Color,
    )

    data class Secondary(
        val secondary: Color,
        val onSecondary: Color,
        val secondaryContainer: Color,
        val onSecondaryContainer: Color,
    )

    data class Tertary(
        val tertiary: Color,
        val onTertiary: Color,
        val tertiaryContainer: Color,
        val onTertiaryContainer: Color,
    )

    data class Neutral(
        val background: Color,
        val onBackground: Color,
        val surface: Color,
        val onSurface: Color,
    )

    data class Other(
        val surfaceVariant: Color,
        val onSurfaceVariant: Color,
        val surfaceTint: Color,
        val inverseSurface: Color,
        val inverseOnSurface: Color,
        val error: Color,
        val onError: Color,
        val errorContainer: Color,
        val onErrorContainer: Color,
        val outline: Color,
        val outlineVariant: Color,
        val scrim: Color,
        val surfaceBright: Color,
        val surfaceDim: Color,
        val surfaceContainer: Color,
        val surfaceContainerHigh: Color,
        val surfaceContainerHighest: Color,
        val surfaceContainerLow: Color,
        val surfaceContainerLowest: Color,
    )
}

fun lightColorScheme() =
    NColor(
        primary =
            NColor.Primary(
                primary = Color(0xFF6750A4),
                onPrimary = Color(0xFFFFFFFF),
                primaryContainer = Color(0xFFEADDFF),
                onPrimaryContainer = Color(0xFF21005D),
                inversePrimary = Color(0xFFD0BCFF),
            ),
        secondary =
            NColor.Secondary(
                secondary = Color(0xFF625B71),
                onSecondary = Color(0xFFFFFFFF),
                secondaryContainer = Color(0xFFE8DEF8),
                onSecondaryContainer = Color(0xFF1D192B),
            ),
        tertary =
            NColor.Tertary(
                tertiary = Color(0xFF7D5260),
                onTertiary = Color(0xFFFFFFFF),
                tertiaryContainer = Color(0xFFFFD8E0),
                onTertiaryContainer = Color(0xFF31111D),
            ),
        neutral =
            NColor.Neutral(
                background = Color(0xFF1C1B1F),
                onBackground = Color(0xFFFFFFFF),
                surface = Color(0xFF1C1B1F),
                onSurface = Color(0xFFFFFFFF),
            ),
        other =
            NColor.Other(
                surfaceVariant = Color(0xFF49454F),
                onSurfaceVariant = Color(0xFFCAC4D0),
                surfaceTint = Color(0xFF6750A4),
                inverseSurface = Color(0xFFE6E1E5),
                inverseOnSurface = Color(0xFF313033),
                error = Color(0xFFB3261E),
                onError = Color(0xFFFFFFFF),
                errorContainer = Color(0xFFF9DEDC),
                onErrorContainer = Color(0xFF410E0B),
                outline = Color(0xFF938F99),
                outlineVariant = Color(0xFF49454F),
                scrim = Color(0xFF000000),
                surfaceBright = Color(0xFF292529),
                surfaceDim = Color(0xFF1C1B1F),
                surfaceContainer = Color(0xFF252327),
                surfaceContainerHigh = Color(0xFF312F33),
                surfaceContainerHighest = Color(0xFF3D3B3F),
                surfaceContainerLow = Color(0xFF1C1B1F),
                surfaceContainerLowest = Color(0xFF19181C),
            ),
    )

fun darkColorScheme() =
    NColor(
        primary =
            NColor.Primary(
                primary = Color(0xFFD0BCFF), // Lighter and brighter primary
                onPrimary = Color(0xFF381E72), // Darker onPrimary for contrast
                primaryContainer = Color(0xFF4F378B), // Darker container
                onPrimaryContainer = Color(0xFFEADDFF), // Lighter onContainer
                inversePrimary = Color(0xFF6750A4), // Original primary for inverse
            ),
        secondary =
            NColor.Secondary(
                secondary = Color(0xFFCCC2DC), // Lighter secondary
                onSecondary = Color(0xFF332D41), // Darker onSecondary
                secondaryContainer = Color(0xFF4A4458), // Darker container
                onSecondaryContainer = Color(0xFFE8DEF8), // Lighter onContainer
            ),
        tertary =
            NColor.Tertary(
                tertiary = Color(0xFFEFB8C8), // Lighter tertiary
                onTertiary = Color(0xFF492532), // Darker onTertiary
                tertiaryContainer = Color(0xFF633B48), // Darker container
                onTertiaryContainer = Color(0xFFFFD8E0), // Lighter onContainer
            ),
        neutral =
            NColor.Neutral(
                background = Color(0xFF1C1B1F), // Dark background
                onBackground = Color(0xFFE6E1E5), // Lighter onBackground
                surface = Color(0xFF1C1B1F), // Dark surface
                onSurface = Color(0xFFE6E1E5), // Lighter onSurface
            ),
        other =
            NColor.Other(
                surfaceVariant = Color(0xFF49454F), // Slightly lighter surface variant
                onSurfaceVariant = Color(0xFFCAC4D0),
                surfaceTint = Color(0xFFD0BCFF), // Use lighter primary as tint
                inverseSurface = Color(0xFF313033), // Darker inverse surface
                inverseOnSurface = Color(0xFFE6E1E5), // Lighter inverse onSurface
                error = Color(0xFFF2B8B5), // Lighter error
                onError = Color(0xFF601410), // Darker onError
                errorContainer = Color(0xFF8C1D18), // Darker container
                onErrorContainer = Color(0xFFF9DEDC), // Lighter onContainer
                outline = Color(0xFF938F99),
                outlineVariant = Color(0xFF49454F),
                scrim = Color(0xFF000000),
                surfaceBright = Color(0xFF1C1B1F), // Same as background
                surfaceDim = Color(0xFF1C1B1F), // Same as background
                surfaceContainer = Color(0xFF252327), // Slightly lighter container
                surfaceContainerHigh = Color(0xFF312F33),
                surfaceContainerHighest = Color(0xFF3D3B3F),
                surfaceContainerLow = Color(0xFF1C1B1F), // Same as background
                surfaceContainerLowest = Color(0xFF19181C),
            ),
    )

val colorScheme = staticCompositionLocalOf { lightColorScheme() }
