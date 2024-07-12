package com.niranjan.khatri.androidcomposetutorial.ds.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable

object NTheme {
    val typography: NTypography
        @Composable
        @ReadOnlyComposable
        get() = nTypography.current

    val shapes: NShapes
        @Composable
        @ReadOnlyComposable
        get() = nShapes.current
}
