package com.niranjan.khatri.androidcomposetutorial.clean_architecture.presentation_layer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.niranjan.khatri.androidcomposetutorial.clean_architecture.data_layer.CoffeeRepository
import com.niranjan.khatri.androidcomposetutorial.clean_architecture.domain_layer.CoffeeImage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * @author NIRANJAN KHATRI
 * @since 22/02/24
 * @version 1
 */
class CoffeeViewModel(
    private val repository: CoffeeRepository,
) : ViewModel() {
    private val _coffeeImage = MutableStateFlow<CoffeeImage>(CoffeeImage())
    val coffeeImage: StateFlow<CoffeeImage> = _coffeeImage.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getRandomCoffeeImage().collect {
                _coffeeImage.emit(it)
            }
        }
    }
}
