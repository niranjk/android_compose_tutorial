package com.niranjan.khatri.androidcomposetutorial.concepts.managing_events.state

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.niranjan.khatri.androidcomposetutorial.concepts.managing_events.channel.LoginScreen
import com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.theme.LocalTypography
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenStateExample(navController: NavController, viewModel: MainViewModelState) {
    val shouldSignOut by remember { viewModel.shouldSignOut }

    LaunchedEffect(key1 = shouldSignOut) {
        if (shouldSignOut) {
            viewModel.performSignOut()
            navController.navigate("login")
            viewModel.resetSignOutState() // Reset the state after navigation
        }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Main Screen State",  style = LocalTypography.current.bodyLarge) }) }
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

class MainViewModelState : ViewModel() {
    private val _shouldSignOut = mutableStateOf(false)
    val shouldSignOut = _shouldSignOut

    fun onSignOutClicked() {
        _shouldSignOut.value = true
    }

    fun performSignOut() {
        viewModelScope.launch {
            // Simulate sign-out process (replace with actual logic)
        }
    }

    fun resetSignOutState() {
        _shouldSignOut.value = false
    }
}

@Composable
fun NavigationState(viewModel: MainViewModelState) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController) }
        composable("main") { MainScreenStateExample(navController, viewModel) }
    }
}

@Preview
@Composable
fun Channel_Preview(){
    val viewModel = viewModel<MainViewModelState>()
    NavigationState(viewModel = viewModel)
}