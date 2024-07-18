package com.niranjan.khatri.androidcomposetutorial.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.niranjan.khatri.androidcomposetutorial.R
import com.niranjan.khatri.androidcomposetutorial.bootcamp.ComposeExpandableScreen

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        ComposeExpandableScreen()
    }
}

@Composable
fun BottomNavigationBarScreen(navController: NavController) {
}

@Composable
fun BottomNavigationScreen(modifier: Modifier = Modifier) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = modifier.height(80.dp),
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null,
                )
            },
            label = {
                Text(stringResource(R.string.label_home))
            },
            selected = true,
            onClick = {
            },
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Face,
                    contentDescription = null,
                )
            },
            label = {
                Text(stringResource(R.string.label_profile))
            },
            selected = false,
            onClick = {
            },
        )
    }
}
