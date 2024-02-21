package com.niranjan.khatri.androidcomposetutorial.mvvm.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.niranjan.khatri.androidcomposetutorial.mvvm.model.DogBreed
import com.niranjan.khatri.androidcomposetutorial.mvvm.viewmodel.DogBreedListViewModel

/**
 * @author NIRANJAN KHATRI
 * @since 21/02/24
 * @version 1
 */

@Composable
fun DogBreedListScreen(viewModel: DogBreedListViewModel) {
    val dogBreeds = viewModel.dogBreeds.collectAsState()

    Column {
        Text(text = "Animal", fontWeight = FontWeight.ExtraBold)
        Spacer(modifier = Modifier.height(16.dp))
        if (dogBreeds.value.isEmpty()) {
            CircularProgressIndicator()
        } else {
            DogBreedList(dogBreeds.value)
        }
    }
}

@Composable
fun DogBreedList(dogBreeds: List<DogBreed>) {
    Column {
        dogBreeds.forEach { breed ->
            DogBreedItem(breed)
        }
    }
}

@Composable
fun DogBreedItem(breed: DogBreed) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            if (breed.url != null) {
                AsyncImage(
                    modifier = Modifier.fillMaxWidth(),
                    model = breed.url,
                    contentDescription = breed.id.toString()
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = breed.id.toString())
        }
    }
}
