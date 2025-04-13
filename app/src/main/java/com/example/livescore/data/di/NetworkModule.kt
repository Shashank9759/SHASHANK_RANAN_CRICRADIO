package com.example.livescore.data.di

import com.example.livescore.data.remote.CricRadioApi
import com.example.livescore.data.remote.CricRadioApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.websocket.pingInterval
import javax.inject.Singleton
import kotlin.time.Duration.Companion.seconds

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideKtorClient(): HttpClient {
        return HttpClient(OkHttp) {
            engine {
                preconfigured = OkHttpClient.Builder()
                    .connectTimeout(120, TimeUnit.SECONDS)
                    .readTimeout(120, TimeUnit.SECONDS)
                    .writeTimeout(120, TimeUnit.SECONDS)
                    .pingInterval(30, TimeUnit.SECONDS)
                    .build()
            }
            install(WebSockets) {
                pingInterval = 30.seconds
            }

            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }

            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        coerceInputValues = true
                    }
                )
            }

            install(WebSockets)

            install(HttpTimeout) {
                connectTimeoutMillis = 120_000
                socketTimeoutMillis  = 120_000
                requestTimeoutMillis = 120_000
            }



        }
    }

    @Provides
    @Singleton
    fun provideCricRadioApi(client: HttpClient): CricRadioApi {
        return CricRadioApiImpl(client)
    }
}
