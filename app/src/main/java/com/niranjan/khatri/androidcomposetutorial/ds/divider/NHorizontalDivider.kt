package com.niranjan.khatri.androidcomposetutorial.ds.divider

import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.niranjan.khatri.androidcomposetutorial.ds.DevicePreview
import com.niranjan.khatri.androidcomposetutorial.ds.theme.LocalColorScheme
import com.niranjan.khatri.androidcomposetutorial.ds.theme.LocalShapes
import com.niranjan.khatri.androidcomposetutorial.ds.theme.NAppTheme


@Composable
fun NHorizontalDivider(
    modifier: Modifier = Modifier,
    color: Color = LocalColorScheme.current.primary.primaryContainer,
    thickness: Dp = LocalShapes.current.space.spaceSmall,
) {
    HorizontalDivider(
        modifier = modifier,
        color = color,
        thickness = thickness,
    )
}

@DevicePreview
@Composable
fun Demo_H()  {
    NAppTheme {
        NHorizontalDivider(
            thickness = 20.dp,
        )
    }
}