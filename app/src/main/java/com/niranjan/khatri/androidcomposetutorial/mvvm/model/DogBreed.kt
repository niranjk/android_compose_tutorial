package com.niranjan.khatri.androidcomposetutorial.mvvm.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author NIRANJAN KHATRI
 * @since 20/02/24
 * @version 1
 */

@Serializable
data class DogBreed(
    val id: String,
    @SerialName("url")
    val url: String? = null
)