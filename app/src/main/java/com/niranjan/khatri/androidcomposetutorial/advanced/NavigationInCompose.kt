package com.niranjan.khatri.androidcomposetutorial.advanced

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

/**
 * @author NIRANJAN KHATRI
 * @since 16/05/24
 * @version 1
 */

@Composable
fun NavigationInCompose() {
    // Create a NavController
    val navController = rememberNavController()

    // Create a NavHost
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("product_details") { ProductDetailsScreen(navController) }
        composable("shopping_cart") { ShoppingCartScreen(navController) }
        composable("checkout") { CheckoutScreen(navController) }
    }

    // Navigate to the product details screen
    navController.navigate("product_details")

    // Navigate up to the home screen
    navController.navigateUp()

    // Clear the back stack
    navController.clearBackStack("home")
}


@Composable
fun PassingArgumentsInNavigation(){
    // Create a NavController
    val navController = rememberNavController()
    // Create a NavHost for your app
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("details/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id").orEmpty()
            DetailsScreen(id)
        }
    }

    // Navigate to the details screen with an ID of 123
    navController.navigate("details/123")

    // Navigate back to the home screen
    navController.popBackStack()

    // Navigate back to the home screen and clear the back stack
    navController.popBackStack("home", inclusive = true)
}

@Composable
fun DetailsScreen(id: String){
    Text(text = id)
}


@Composable
fun NestedNavigationExample(){
    // Create a NavController
    val navController = rememberNavController()
    // Create a NavHost for the main navigation graph
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("settings") {
            // Create a nested NavHost for the settings navigation graph
            NavHost(navController = navController, startDestination = "general") {
                composable("details") { DetailsScreen("123") }
                composable("checkout") { CheckoutScreen(navController) }
            }
        }
    }
}

@Composable
fun DeepLinkingNavigation(){
    // Create a NavController
    val navController = rememberNavController()
    // Create a deep link to the details screen with an ID of 123
    val deepLink = Uri.parse("rally://details/123")

    // Navigate to the details screen using the deep link
    navController.navigate(deepLink)
}

// Create a custom navigation component that allows you to navigate to a specific destination when a button is clicked
@Composable
fun CustomNavigationButton(
    navController: NavHostController,
    destination: String
) {
    Button(onClick = { navController.navigate(destination) }) {
        Text("Navigate to $destination")
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Checkout") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) {
        // Rest of the CheckoutScreen content
        Column(modifier = Modifier.padding(it)) {
            Text(text = "Checkout Items")
        }
    }
}

@Composable
fun ShoppingCartScreen(navController: NavHostController) {
    // ...
}

@Composable
fun ProductDetailsScreen(navController: NavHostController) {
    // ...
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Home") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) {
        // Rest of the CheckoutScreen content
        Column(modifier = Modifier.padding(it)) {
            Text(text = "Home Items")
        }
    }
}


