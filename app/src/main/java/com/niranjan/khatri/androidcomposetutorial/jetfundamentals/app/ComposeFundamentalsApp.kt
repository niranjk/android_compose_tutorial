package com.niranjan.khatri.androidcomposetutorial.jetfundamentals.app

import androidx.compose.animation.Crossfade
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.niranjan.khatri.androidcomposetutorial.jetfundamentals.router.ComposeFundamentalRouter
import com.niranjan.khatri.androidcomposetutorial.jetfundamentals.router.Screen
import com.niranjan.khatri.androidcomposetutorial.jetfundamentals.screens.AlertDialogScreen
import com.niranjan.khatri.androidcomposetutorial.jetfundamentals.screens.NavigationScreen
import com.niranjan.khatri.androidcomposetutorial.jetfundamentals.screens.ProgressIndicatorScreen
import com.niranjan.khatri.androidcomposetutorial.jetfundamentals.screens.TextFieldScreen
import com.niranjan.khatri.androidcomposetutorial.jetfundamentals.screens.TextScreen

/**
 * @author NIRANJAN KHATRI
 * @since 21/03/24
 * @version 1
 */

@Composable
fun ComposeFundamentalsApp(){
    Surface(color = MaterialTheme.colorScheme.background){
        Crossfade(targetState = ComposeFundamentalRouter.currentScreen, label = "Crossfade Label") { screenState ->
            when (screenState.value){
                is Screen.Navigation -> NavigationScreen()
                is Screen.Text -> TextScreen()
                is Screen.TextField -> TextFieldScreen()
                is Screen.AlertDialog -> AlertDialogScreen()
                is Screen.ProgressIndicator -> ProgressIndicatorScreen()
                else -> TextScreen()
            }
        }
    }
}