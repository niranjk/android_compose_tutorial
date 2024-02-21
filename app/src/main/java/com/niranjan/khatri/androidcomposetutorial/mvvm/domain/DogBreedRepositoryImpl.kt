package com.niranjan.khatri.androidcomposetutorial.mvvm.domain

import com.niranjan.khatri.androidcomposetutorial.mvvm.model.DogBreed
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * @author NIRANJAN KHATRI
 * @since 21/02/24
 * @version 1
 */
class DogBreedRepositoryImpl : DogBreedRepository{
    override suspend fun getDogBreeds(): Flow<List<DogBreed>> {
        return flow {
            val breeds = DogApiClient.getDogBreeds()
            emit(breeds)
        }
    }
}