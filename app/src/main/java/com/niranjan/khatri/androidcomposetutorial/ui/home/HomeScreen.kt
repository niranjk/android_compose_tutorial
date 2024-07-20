package com.niranjan.khatri.androidcomposetutorial.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.niranjan.khatri.androidcomposetutorial.R
import com.niranjan.khatri.androidcomposetutorial.demo
import com.niranjan.khatri.androidcomposetutorial.ds.theme.LocalTypography
import com.niranjan.khatri.androidcomposetutorial.home

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = stringResource(id = R.string.label_home), style = LocalTypography.current.bodyLarge)
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun BottomNavigationScreen(
    modifier: Modifier = Modifier,
    navigationController: NavController,
) {
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
                navigationController.navigate(home.route)
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
                Text(stringResource(R.string.label_demo))
            },
            selected = false,
            onClick = {
                navigationController.navigate(demo.route)
            },
        )
    }
}
