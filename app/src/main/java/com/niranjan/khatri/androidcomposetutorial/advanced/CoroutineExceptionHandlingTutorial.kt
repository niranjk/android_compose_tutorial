package com.niranjan.khatri.androidcomposetutorial.advanced

import android.accounts.NetworkErrorException
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import java.io.IOException
import kotlin.coroutines.cancellation.CancellationException
import kotlin.random.Random


class ExceptionHandlingExampleViewModel(
    private val myRepository: MyRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<MyUiState>(MyUiState.Loading)
    val uiState: StateFlow<MyUiState> = _uiState.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        // Handle the exception globally, e.g., log it or show a generic error message
        Log.e("GlobalExceptionHandler", "Caught exception: ${exception.message}")
        // Assuming you have a way to display errors, e.g., using a LiveData or StateFlow
        _uiState.value = MyUiState.Error(exception.message ?: "Something went wrong")
    }

    init {
        featchData()
    }

    private fun featchData() {
        viewModelScope.launch {
            myRepository.getData()
                .catch { exception ->
                    _uiState.value = MyUiState.Error(exception.message ?: "Unknown error")
                }
                .collect { data ->
                    _uiState.value = MyUiState.Success(data)
                }
        }
    }

    fun usingCoroutineExceptionHandler() {
        viewModelScope.launch(coroutineExceptionHandler) {
            myRepository.getData()
                .catch { exception ->
                    _uiState.value = MyUiState.Error(exception.message ?: "Unknown error")
                }
                .collect { data ->
                    _uiState.value = MyUiState.Success(data)
                }
        }
    }

    fun childCoroutineExceptionHandler() {
        viewModelScope.launch {
            try {
                launch {
                    // Child coroutine: potential exception here
                    throw IOException("Network error")
                }
                // ... other code that should continue even if the child fails ...
            } catch (e: IOException) {
                // Handle IOException from the child coroutine
                _uiState.value = MyUiState.Error(e.message ?: "Network Exception")
            }
        }
    }


    fun cancellingExceptionExample() {
        viewModelScope.launch {
            try {
                myRepository.getData()
                    .flowOn(Dispatchers.IO) // Perform network request on IO dispatcher
                    .onStart { _uiState.value = MyUiState.Loading } // Show loading state
                    .catch { e ->
                        // Handle exceptions from the Flow
                        if (e is CancellationException) {
                            Log.d("Coroutine", "Coroutine canceled")
                        } else {
                            _uiState.value =
                                MyUiState.Error(e.message ?: "An unknown error occurred")
                        }
                    }
                    .collect { data ->
                        // Update UI with the fetched data
                        _uiState.value = MyUiState.Success(data)
                    }
            } catch (e: Exception) {
                // Handle any exceptions outside the Flow collection
                _uiState.value = MyUiState.Error(e.message ?: "An unknown error occurred")
            }
        }
    }
}

class MyRepository {
    fun getData(): Flow<List<MyUiModel>> {
        return flow {
            MyUiModel()
        }
    }
}

sealed class MyUiState {
    data object Loading : MyUiState()
    data class Success(val chats: List<MyUiModel>) : MyUiState()
    data class Error(val message: String) : MyUiState()
}

data  class MyUiModel(
    val id : Int = Random.nextInt(),
    val message : String = ""
)



class MyExceptionViewModel : ViewModel() {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        Log.e("GlobalExceptionHandler", "Caught exception: ${exception.message}")
        // Show a generic error message or navigate to an error screen
        showError("Something went wrong")
    }

    fun fetchData() {
        viewModelScope.launch(coroutineExceptionHandler) {
            try {
                // Fetch data on IO dispatcher
                val data = withContext(Dispatchers.IO) { fetchUserData() }
                // Update UI on Main dispatcher
                withContext(Dispatchers.Main) { updateUI(data) }
            } catch (e: NetworkErrorException) {
                // Handle specific network errors
                showError("Network connection error")
            }
        }
    }

    // ... other ViewModel code ...

    private suspend fun fetchUserData(): MyUiModel {
        // Simulate fetching user data (e.g., from a network request)
        // ...
        // Throw NetworkErrorException if a network error occurs
        // ...
        return MyUiModel()
    }

    private fun updateUI(data: MyUiModel) {
        // Update UI elements with the fetched data
        // ...
    }

    private fun showError(message: String) {
        // Display an error message to the user (e.g., using a Snackbar)
        // ...
    }
}