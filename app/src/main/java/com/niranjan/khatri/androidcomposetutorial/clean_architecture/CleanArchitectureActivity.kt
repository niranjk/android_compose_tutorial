package com.niranjan.khatri.androidcomposetutorial.clean_architecture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.niranjan.khatri.androidcomposetutorial.clean_architecture.data_layer.CoffeeRepositoryImpl
import com.niranjan.khatri.androidcomposetutorial.clean_architecture.presentation_layer.CoffeeViewModel
import com.niranjan.khatri.androidcomposetutorial.clean_architecture.ui_layer.CoffeeScreen

/**
 * @author NIRANJAN KHATRI
 * @since 22/02/24
 * @version 1
 */
class CleanArchitectureActivity : ComponentActivity() {
    val repository = CoffeeRepositoryImpl()
    val viewModel = CoffeeViewModel(repository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoffeeScreen(viewModel = viewModel)
        }

    }
}