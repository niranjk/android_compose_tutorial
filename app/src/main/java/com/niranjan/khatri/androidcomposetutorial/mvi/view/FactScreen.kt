package com.niranjan.khatri.androidcomposetutorial.mvi.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.niranjan.khatri.androidcomposetutorial.mvi.intent.FactIntent
import com.niranjan.khatri.androidcomposetutorial.mvi.intent.FactViewModel
import com.niranjan.khatri.androidcomposetutorial.mvi.model.FactState

/**
 * @author NIRANJAN KHATRI
 * @since 21/02/24
 * @version 1
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FactScreen(viewModel: FactViewModel) {
    val state = viewModel.state.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.dispatch(FactIntent.LoadFact) },
            ) {
                Icon(Icons.Outlined.Refresh, contentDescription = "Refresh")
            }
        }
    ){
        Box(modifier = Modifier.fillMaxSize().padding(it)) {
            when (val currentState = state.value) {
                FactState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is FactState.Success -> {
                    Text(
                        text = currentState.fact.text,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 30.sp
                    )
                }
                is FactState.Error -> {
                    Text(
                        text = "Error: ${currentState.exception.message}",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }

}
