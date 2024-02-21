package com.niranjan.khatri.androidcomposetutorial.mvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.niranjan.khatri.androidcomposetutorial.mvvm.domain.DogBreedRepository
import com.niranjan.khatri.androidcomposetutorial.mvvm.model.DogBreed
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * @author NIRANJAN KHATRI
 * @since 21/02/24
 * @version 1
 */
class DogBreedListViewModel(private val repository: DogBreedRepository) : ViewModel() {

    private var _dogBreeds = MutableStateFlow<List<DogBreed>>(emptyList())
    val dogBreeds: StateFlow<List<DogBreed>> = _dogBreeds.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getDogBreeds().collect{
                _dogBreeds.emit(it)
            }
        }
    }
}