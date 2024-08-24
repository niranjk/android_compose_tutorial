package com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.theme.NAppTheme

sealed class AppScreen(
    val icon: ImageVector,
    val label: String,
    val route: String
) {
    object Home : AppScreen(Icons.Filled.Home, "Home", "home")
    object Collections : AppScreen(Icons.Filled.Favorite, "Collections", "collections")
    object UserProfile : AppScreen(Icons.Filled.Face, "Profile", "profile")
    object Settings : AppScreen(Icons.Filled.Settings, "Settings", "settings")
    object Mail : AppScreen(Icons.Filled.MailOutline, "Mails", "mails")
}
@Composable
fun NavigationApp() {
    NAppTheme {
        val navController = rememberNavController()
        val onBack: () -> Unit = {
            navController.popBackStack()
        }
        val screens = listOf(
            AppScreen.Home,
            AppScreen.Collections,
            AppScreen.UserProfile,
            AppScreen.Mail,
            AppScreen.Settings
        )
        val showBottomBar = navController
            .currentBackStackEntryAsState().value?.destination?.route in screens.map { it.route }
        Scaffold(
            bottomBar = {
                if (showBottomBar) {
                    NavigationBar {
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentDestination = navBackStackEntry?.destination
                        screens.forEach { screen ->
                            NavigationBarItem(
                                icon = { Icon(imageVector = screen.icon, contentDescription = screen.label) },
                                label = { Text(screen.label) },
                                selected = currentDestination?.route == screen.route,
                                onClick = {
                                    navController.navigate(screen.route) {
                                        // Pop up to the start destination of the graph to
                                        // avoid building up a large stack of destinations
                                        // on the back stack as users select items
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        // Avoid multiple copies of the same destination when
                                        // reselecting the same item
                                        launchSingleTop = true
                                        // Restore state when reselecting a previously selected item
                                        restoreState = true
                                    }
                                }
                            )
                        }
                    }
                }
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                // nav graph
                NavHost(navController = navController, startDestination = AppScreen.Home.route) {
                    // add new navigation function
                    composable(AppScreen.Home.route) { MyScreen(title = "Home Screen") }
                    composable(AppScreen.Collections.route) { MyScreen(title = "Collection Screen") }
                    composable(AppScreen.UserProfile.route) { MyScreen(title = "UserProfile Screen") }
                    composable(AppScreen.Settings.route) { MyScreen(title = "Settings Screen") }
                    composable(AppScreen.Mail.route) { MyScreen(title = "Mails Screen") }
                }
            }
        }
    }
}

@Composable
fun MyScreen(title: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

@Preview
@Composable
fun NavigationApp_Preview(){
    NavigationApp()
}