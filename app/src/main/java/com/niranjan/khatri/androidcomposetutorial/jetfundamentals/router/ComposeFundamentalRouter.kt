package com.niranjan.khatri.androidcomposetutorial.jetfundamentals.router

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

/**
 * @author NIRANJAN KHATRI
 * @since 21/03/24
 * @version 1
 */

sealed class Screen{
    object Navigation: Screen()
    object Text: Screen()
    object TextField: Screen()
    object Buttons: Screen()
    object AlertDialog : Screen()
    object ProgressIndicator : Screen()
}
object ComposeFundamentalRouter {
    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.Navigation)
    fun navigateTo(destination: Screen){
        currentScreen.value = destination
    }
}