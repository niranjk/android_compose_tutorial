package com.niranjan.khatri.androidcomposetutorial.notesapp.presentation_layer

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.niranjan.khatri.androidcomposetutorial.notesapp.data_layer.repository.Repository

/**
 * @author NIRANJAN KHATRI
 * @since 27/03/24
 * @version 1
 */
@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(
    owner: SavedStateRegistryOwner,
    private val repository: Repository,
    private val defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return MainViewModel(repository) as T
    }
}