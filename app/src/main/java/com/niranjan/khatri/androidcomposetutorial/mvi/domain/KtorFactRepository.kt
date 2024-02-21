package com.niranjan.khatri.androidcomposetutorial.mvi.domain

import com.niranjan.khatri.androidcomposetutorial.mvi.model.Fact
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
class KtorFactRepository: FactRepository {
    private val baseUrl = "https://uselessfacts.jsph.pl/api/v2/facts/"
    private val nonStrictJson = Json { isLenient = true; ignoreUnknownKeys = true }
    override suspend fun getRandomFact(): Fact {
        val client = HttpClient {
            install(ContentNegotiation) {
                json(nonStrictJson)
            }
        }
        val url = URLBuilder(baseUrl).appendEncodedPathSegments("random").build()
        val response = client.get(url)
        return response.body()
    }
}