package com.example.livescore.data.remote


import com.example.livescore.data.model.minicard.Minicard
import com.example.livescore.data.model.venue.Venue

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.timeout
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter

class CricRadioApiImpl(
    private val client: HttpClient
) : CricRadioApi {

    companion object {
        private const val BASE_URL = "http://3.6.243.12:5001"
        private const val AUTH_HEADER = "Basic Y3JpY2tldFJhZGlvOmNyaWNrZXRAJCUjUmFkaW8xMjM="
    }

    override suspend fun getMiniMatchCard(key: String): Minicard {
        return  client.get("$BASE_URL/api/v2/match/mini-match-card") {
            header("Authorization", AUTH_HEADER)
            parameter("key", key)
            timeout {
                requestTimeoutMillis = 120_000
                socketTimeoutMillis = 120_000
            }
        }.body()
    }

    override suspend fun getVenueInfo(key: String): Venue {
        return client.get("$BASE_URL/api/v2/match/venue-info") {
            header("Authorization", AUTH_HEADER)
            parameter("key", key)
            timeout {
                requestTimeoutMillis = 120_000
                socketTimeoutMillis = 120_000
            }
        }.body()
    }
}
