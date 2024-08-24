package com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandardNavigationDrawer() {
    val drawerState =rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val items = listOf(
        "Home" to Icons.Filled.Home,
        "Profile" to Icons.Filled.Person,
        "Settings" to Icons.Filled.Settings,
        "Messages" to Icons.Filled.MailOutline,
        "Favorites" to Icons.Filled.Favorite,
        "Downloads" to Icons.Filled.Done,
        "History" to Icons.Filled.Person,
        "Shared" to Icons.Filled.Share,
        "Notifications" to Icons.Filled.Notifications
    )
    val selectedItem = remember { mutableStateOf(items[0].first) }
    PermanentNavigationDrawer(
        drawerContent = {
            PermanentDrawerSheet(modifier = Modifier.wrapContentSize()) {
                Column(modifier= Modifier.verticalScroll(rememberScrollState())) {
                    items.forEach { item ->
                        NavigationRailItem(
                            icon = { Icon(item.second, contentDescription = item.first) },
                            label = { Text(item.first) },
                            selected = selectedItem.value == item.first,
                            onClick = {
                                scope.launch { drawerState.close() }
                                selectedItem.value = item.first
                            }
                        )
                    }
                }
            }
        },
        content = {
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        title = { Text("Scrollable Navigation Drawer") },
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch {
                                    if (drawerState.isClosed) drawerState.open() else drawerState.close()
                                }
                            }) {
                                Icon(Icons.Filled.Menu, contentDescription = "Menu")
                            }
                        }
                    )
                }
            ) { padding ->
                Text(text = "Selected item: ${selectedItem.value}", modifier = Modifier.padding(padding))
            }}
    )
}

@Preview
@Composable
fun StandardNavigationDrawer_Preview(){
    StandardNavigationDrawer()
}

// Modal Navigation drawer
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NModalNavigationDrawer() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val items = listOf(
        "Home" to Icons.Filled.Home,
        "Profile" to Icons.Filled.Person,
        "Settings" to Icons.Filled.Settings,
        "Messages" to Icons.Filled.MailOutline,
        "Favorites" to Icons.Filled.Favorite,
        "Downloads" to Icons.Filled.Done,
        "History" to Icons.Filled.Person,
        "Shared" to Icons.Filled.Share,
        "Notifications" to Icons.Filled.Notifications
    )
    val selectedItem = remember { mutableStateOf(items[0].first) }
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    items.forEach { item ->
                        NavigationDrawerItem(
                            icon = { Icon(item.second, contentDescription = item.first) },
                            label = { Text(item.first) },
                            selected = selectedItem.value == item.first,
                            onClick = {
                                scope.launch { drawerState.close() }
                                selectedItem.value = item.first
                            }
                        )
                    }
                }
            }
        },
        content = {
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        title = { Text("Modal Navigation Drawer") },
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch { drawerState.open() }
                            }) {
                                Icon(Icons.Filled.Menu, contentDescription = "Menu")
                            }
                        }
                    )
                }
            ) { padding ->
                Text(text = "Selected item: ${selectedItem.value}", modifier = Modifier.padding(padding))
            }
        }
    )
}

@Preview
@Composable
fun ModalNavigationDrawer_Preview(){
    NModalNavigationDrawer()
}