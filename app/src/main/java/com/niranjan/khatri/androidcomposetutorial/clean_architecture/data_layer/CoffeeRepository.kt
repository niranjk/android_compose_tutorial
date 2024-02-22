package com.niranjan.khatri.androidcomposetutorial.clean_architecture.data_layer

import com.niranjan.khatri.androidcomposetutorial.clean_architecture.domain_layer.CoffeeImage
import kotlinx.coroutines.flow.Flow

/**
 * @author NIRANJAN KHATRI
 * @since 22/02/24
 * @version 1
 */
interface CoffeeRepository {
    suspend fun getRandomCoffeeImage(): Flow<CoffeeImage>
}