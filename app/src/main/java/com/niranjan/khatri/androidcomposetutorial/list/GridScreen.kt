package com.niranjan.khatri.androidcomposetutorial.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.niranjan.khatri.androidcomposetutorial.grid.GridIcon

/**
 * @author NIRANJAN KHATRI
 * @since 22/03/24
 * @version 1
 */

private val gridItems = listOf(
    Icons.Filled.Face,
    Icons.Filled.AccountBox,
    Icons.Filled.Build,
    Icons.Filled.Call,
    Icons.Filled.Delete,
    Icons.Filled.Email,
    Icons.Filled.Warning,
    Icons.Filled.Home,
    Icons.Filled.Info
)

@Preview(showBackground = true)
@Composable
fun GridScreen(){
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Adaptive(50.dp)){
        items(gridItems.size){ index ->
            GridIcon(iconResId = gridItems[index])
        }
    }
}