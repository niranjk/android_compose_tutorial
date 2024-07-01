package com.niranjan.khatri.androidcomposetutorial.clean_architecture.domain_layer

import kotlinx.serialization.Serializable

/**
 * @author NIRANJAN KHATRI
 * @since 22/02/24
 * @version 1
 */
@Serializable
data class CoffeeImage(
    val file: String? = null,
)
