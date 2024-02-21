package com.niranjan.khatri.androidcomposetutorial.mvi.intent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.niranjan.khatri.androidcomposetutorial.mvi.domain.FactRepository
import com.niranjan.khatri.androidcomposetutorial.mvi.model.FactState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * @author NIRANJAN KHATRI
 * @since 21/02/24
 * @version 1
 */
class FactViewModel(private val repository: FactRepository) : ViewModel() {

    private val _state = MutableStateFlow<FactState>(FactState.Loading)
    val state: StateFlow<FactState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                val fact = repository.getRandomFact()
                _state.value = FactState.Success(fact)
            } catch (e: Exception) {
                _state.value = FactState.Error(e)
            }
        }
    }

    fun dispatch(intent: FactIntent) {
        when (intent) {
            FactIntent.LoadFact -> loadFact()
        }
    }

    private fun loadFact() {
        viewModelScope.launch {
            try {
                val fact = repository.getRandomFact()
                _state.value = FactState.Success(fact)
            } catch (e: Exception) {
                _state.value = FactState.Error(e)
            }
        }
    }
}
