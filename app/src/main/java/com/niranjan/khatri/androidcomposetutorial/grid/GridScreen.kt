package com.niranjan.khatri.androidcomposetutorial.grid

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.niranjan.khatri.androidcomposetutorial.R

/**
 * @author NIRANJAN KHATRI
 * @since 21/03/24
 * @version 1
 */

private val gridItems = listOf(
    Icons.Filled.Face,
    Icons.Filled.AccountBox,
    Icons.Filled.Add,
    Icons.Filled.AccountCircle,
    Icons.Filled.AddCircle,
    Icons.Filled.ExitToApp,
    Icons.Filled.Call,
    Icons.Filled.Delete,
    Icons.Filled.Email,
    Icons.Filled.Warning,
    Icons.Filled.Home
)

@Composable
fun GridSceen() {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(2),
        content = {
            items(gridItems.size) { index ->
                GridIcon(iconResId = gridItems[index])
            }
        })
}


@Composable
fun GridIcon(iconResId: ImageVector) {
    Icon(
        imageVector = iconResId,
        tint = colorResource(id = R.color.colorPrimary),
        contentDescription = stringResource(id = R.string.compose),
        modifier = Modifier
            .size(80.dp)
            .padding(20.dp)
    )
}