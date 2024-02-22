package com.niranjan.khatri.androidcomposetutorial.clean_architecture.ui_layer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import com.niranjan.khatri.androidcomposetutorial.clean_architecture.presentation_layer.CoffeeViewModel

/**
 * @author NIRANJAN KHATRI
 * @since 22/02/24
 * @version 1
 */
@Composable
fun CoffeeScreen(viewModel: CoffeeViewModel) {
    val image = viewModel.coffeeImage.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            modifier = Modifier.fillMaxWidth(),
            model = image.value.file,
            contentDescription = "Coffee Image"
        )
    }
}