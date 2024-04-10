package com.niranjan.khatri.androidcomposetutorial.notesapp.presentation_layer

/**
 * @author NIRANJAN KHATRI
 * @since 27/03/24
 * @version 1
 */
import androidx.lifecycle.ViewModel
import com.niranjan.khatri.androidcomposetutorial.notesapp.data_layer.repository.Repository

/**
 * View model used for storing the global app state.
 *
 * This view model is used for all screens.
 */
class MainViewModel(private val repository: Repository) : ViewModel() {

}