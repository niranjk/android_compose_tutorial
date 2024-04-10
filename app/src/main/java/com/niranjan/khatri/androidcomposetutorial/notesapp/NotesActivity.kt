package com.niranjan.khatri.androidcomposetutorial.notesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.niranjan.khatri.androidcomposetutorial.ComposeTutorialApp
import com.niranjan.khatri.androidcomposetutorial.notesapp.presentation_layer.MainViewModel
import com.niranjan.khatri.androidcomposetutorial.notesapp.presentation_layer.MainViewModelFactory
import com.niranjan.khatri.androidcomposetutorial.notesapp.ui_layer.AppDrawer
import com.niranjan.khatri.androidcomposetutorial.notesapp.ui_layer.Note
import com.niranjan.khatri.androidcomposetutorial.notesapp.ui_layer.RouteScreen
import com.niranjan.khatri.androidcomposetutorial.ui.theme.AndroidComposeTutorialTheme
import kotlinx.coroutines.launch

/**
 * @author NIRANJAN KHATRI
 * @since 27/03/24
 * @version 1
 */
class NotesActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels(factoryProducer = {
        MainViewModelFactory(
            this,
            (application as ComposeTutorialApp).dependencyInjector.repository
        )
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // Setting the content
        setContent { // Starting Point
            AndroidComposeTutorialTheme {
                NoteAppContent()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteAppContent() {

    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            AppDrawer(
                currentRouteScreen = RouteScreen.Notes,
                onRouteScreenSelected = { screen ->
                    coroutineScope.launch {
                        drawerState.close()
                    }
                })
        }) {
        Note()
    }
}