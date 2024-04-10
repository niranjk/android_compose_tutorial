package com.niranjan.khatri.androidcomposetutorial.notesapp.ui_layer

/**
 * @author NIRANJAN KHATRI
 * @since 27/03/24
 * @version 1
 */
/**
 * Class defining all possible screens in the app.
 */
sealed class RouteScreen(val route: String) {
    object Notes : RouteScreen("Notes")
    object SaveNote : RouteScreen("SaveNote")
    object Trash : RouteScreen("Trash")
}