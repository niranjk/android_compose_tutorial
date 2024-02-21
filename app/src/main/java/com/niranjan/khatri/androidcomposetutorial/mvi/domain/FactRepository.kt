package com.niranjan.khatri.androidcomposetutorial.mvi.domain

import com.niranjan.khatri.androidcomposetutorial.mvi.model.Fact

/**
 * @author NIRANJAN KHATRI
 * @since 21/02/24
 * @version 1
 */
interface FactRepository {
    suspend fun getRandomFact(): Fact
}
