package com.niranjan.khatri.androidcomposetutorial.mvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.niranjan.khatri.androidcomposetutorial.mvvm.domain.DogBreedRepositoryImpl
import com.niranjan.khatri.androidcomposetutorial.mvvm.view.DogBreedListScreen
import com.niranjan.khatri.androidcomposetutorial.mvvm.viewmodel.DogBreedListViewModel

/**
 * @author NIRANJAN KHATRI
 * @since 21/02/24
 * @version 1
 */
class DogMVVMActivity : ComponentActivity() {

    val repository = DogBreedRepositoryImpl()
    val viewmodel = DogBreedListViewModel(repository)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DogBreedListScreen(viewModel = viewmodel)
        }
    }
}