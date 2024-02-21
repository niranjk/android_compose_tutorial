package com.niranjan.khatri.androidcomposetutorial.mvi.model

import kotlinx.serialization.Serializable

/**
 * @author NIRANJAN KHATRI
 * @since 21/02/24
 * @version 1
 */

sealed class FactState{
    object Loading : FactState()
    data class Success(val fact: Fact) : FactState()
    data class Error(val exception: Throwable) : FactState()
}

@Serializable
data class Fact(
    val id: String,
    val text: String
)