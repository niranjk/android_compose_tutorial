package com.niranjan.khatri.androidcomposetutorial.clean_architecture.network_layer

import com.niranjan.khatri.androidcomposetutorial.clean_architecture.domain_layer.CoffeeImage
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.URLBuilder
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


object CoffeeApi {
    private const val baseUrl = "https://coffee.alexflipnote.dev/random.json"
    private val nonStrictJson = Json { isLenient = true; ignoreUnknownKeys = true }

    suspend fun getCoffeeImage(): CoffeeImage {
        val client = HttpClient {
            install(ContentNegotiation) {
                json(nonStrictJson)
            }
        }
        val url = URLBuilder(baseUrl).build()
        val response = client.get(url)
        return response.body()
    }
}