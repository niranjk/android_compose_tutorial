package com.niranjan.khatri.androidcomposetutorial.concepts.managing_events.channel

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.theme.LocalTypography
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel) {
    val context = LocalContext.current
    val signOutEvent by viewModel.signOutEvent.collectAsState(null) // collect your Event State

    LaunchedEffect(signOutEvent) {
        signOutEvent?.let {
            // Perform sign-out logic here (e.g., clear user data, revoke tokens)
            // For this example, we'll just simulate a delay
            // In a real app, you would handle actual sign-out operations
            viewModel.performSignOut()
            navController.navigate("login")
        }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Main Screen",  style = LocalTypography.current.bodyLarge) }) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome to the Main Screen!", style = LocalTypography.current.bodyLarge)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { viewModel.onSignOutClicked() }) {
                Text("Sign Out")
            }
        }
    }
}

class MainViewModel : ViewModel() {
    private val _signOutEvent = Channel<Unit>() // Using Channel for handeling Event..
    val signOutEvent = _signOutEvent.receiveAsFlow()

    fun onSignOutClicked() {
        viewModelScope.launch {
            _signOutEvent.send(Unit)
        }
    }

    fun performSignOut() {
        // Simulate sign-out process (replace with actual logic in a real app)
        viewModelScope.launch {
            // Simulate a delay for sign-out operation
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Login Screen", style = LocalTypography.current.bodyLarge) }) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome to the Login Screen!",  style = LocalTypography.current.bodyLarge)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.navigate("main") }) {
                Text("Login")
            }
        }
    }
}


@Composable
fun Navigation(viewModel: MainViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController) }
        composable("main") { MainScreen(navController, viewModel) }
    }
}

@Preview
@Composable
fun Channel_Preview(){
    val viewModel = viewModel<MainViewModel>()
    Navigation(viewModel = viewModel)
}