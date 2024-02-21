package com.niranjan.khatri.androidcomposetutorial.mvvm.domain

import com.niranjan.khatri.androidcomposetutorial.mvvm.model.DogBreed
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.URLBuilder
import io.ktor.http.appendEncodedPathSegments
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

/**
 * @author NIRANJAN KHATRI
 * @since 21/02/24
 * @version 1
 */
object DogApiClient {
    private const val baseUrl = "https://api.thecatapi.com/v1/images/"
    private val nonStrictJson = Json { isLenient = true; ignoreUnknownKeys = true }

    suspend fun getDogBreeds(): List<DogBreed> {
        val client = HttpClient {
            install(ContentNegotiation) {
                json(nonStrictJson)
            }
        }
        val url = URLBuilder(baseUrl).appendEncodedPathSegments("search").build()
        val response = client.get(url)
        return response.body()
    }
}