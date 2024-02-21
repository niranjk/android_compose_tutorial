package com.niranjan.khatri.androidcomposetutorial.mvvm.domain

import com.niranjan.khatri.androidcomposetutorial.mvvm.model.DogBreed
import kotlinx.coroutines.flow.Flow

/**
 * @author NIRANJAN KHATRI
 * @since 21/02/24
 * @version 1
 */

interface DogBreedRepository {
    suspend fun getDogBreeds(): Flow<List<DogBreed>>
}