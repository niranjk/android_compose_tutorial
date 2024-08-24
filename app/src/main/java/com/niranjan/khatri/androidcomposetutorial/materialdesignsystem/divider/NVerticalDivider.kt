package com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.divider

import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.DevicePreview
import com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.theme.LocalColorScheme
import com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.theme.LocalShapes
import com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.theme.NAppTheme

@Composable
fun NVerticalDivider(
    modifier: Modifier = Modifier,
    color: Color = LocalColorScheme.current.primary.primaryContainer,
    thickness: Dp = LocalShapes.current.space.spaceSmall,
) {
    VerticalDivider(
        modifier = modifier,
        color = color,
        thickness = thickness,
    )
}

@DevicePreview
@Composable
fun Demo()  {
    NAppTheme {
        NVerticalDivider(
            thickness = 20.dp,
        )
    }
}
