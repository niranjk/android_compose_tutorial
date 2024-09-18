package com.niranjan.khatri.androidcomposetutorial.concepts.navigation

import androidx.compose.compiler.plugins.kotlin.EmptyFunctionMetrics
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

// Define your serializable screens
@Serializable
sealed class Screen {
    @Serializable
    data object Home : Screen()
    @Serializable
    data class Details(val itemId: Int) : Screen()
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Home) {
        composable<Screen.Home> {
            HomeScreen(navController)
        }
        composable<Screen.Details> { backStackEntry ->
            val detailsScreen = backStackEntry.toRoute<Screen.Details>()
            DetailsScreen(itemId = detailsScreen.itemId)
        }
        composable(
            route = "profile/{userId}",
            arguments = listOf(navArgument("userId") { type = NavType.StringType }),
            deepLinks = listOf(navDeepLink { uriPattern = "myapp://profile/{userId}" })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")
            ProfileScreen(userId)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(userId: String?) {
    var profile by remember { mutableStateOf<UserProfile?>(null) }

    // Fetch user profile (replace with your actual data source)
    LaunchedEffect(userId) {
        if (userId != null) {
            profile = fetchUserProfile(userId)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Profile") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (profile == null) {
                CircularProgressIndicator()
            } else {
                Text("User ID: ${profile?.id}")
                Text("Name: ${profile?.name}")
                // Add more profile details as needed
            }
        }
    }
}

data class UserProfile(
    val id: String,
    val name: String,
    // ... other profile attributes
)

// Replace with your actual profile fetching logic
suspend fun fetchUserProfile(userId: String): UserProfile? {
    return UserProfile(id = userId, name = "John Doe")
}

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Home Screen")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate(Screen.Details(itemId = 123)) }) {
            Text("Go to Details")
        }
    }
}

@Composable
fun DetailsScreen(itemId: Int) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Details Screen")
        Spacer(modifier = Modifier.height(16.dp))
        Text("Item ID: $itemId")
    }
}