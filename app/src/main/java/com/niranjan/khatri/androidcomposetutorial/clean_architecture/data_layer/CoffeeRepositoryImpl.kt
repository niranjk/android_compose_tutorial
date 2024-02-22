package com.niranjan.khatri.androidcomposetutorial.clean_architecture.data_layer

import com.niranjan.khatri.androidcomposetutorial.clean_architecture.domain_layer.CoffeeImage
import com.niranjan.khatri.androidcomposetutorial.clean_architecture.network_layer.CoffeeApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * @author NIRANJAN KHATRI
 * @since 22/02/24
 * @version 1
 */
class CoffeeRepositoryImpl : CoffeeRepository {
    override suspend fun getRandomCoffeeImage(): Flow<CoffeeImage> =
        flow {
            val coffeeImage = CoffeeApi.getCoffeeImage()
            emit(coffeeImage)
        }
}