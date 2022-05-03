package com.mohamad.kmmplaces.network.http_client

import com.mohamad.kmmplaces.network.foursquare.FoursquareApiKeyProvider
import com.mohamad.kmmplaces.network.http_client.HttpClientOptions.Foursquare.Companion.baseUrl
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

sealed class HttpClientOptions {
    class Foursquare(
        val apiKeyProvider: FoursquareApiKeyProvider
    ) : HttpClientOptions() {
        // TODO: add support for api versioning
        companion object {
            const val baseUrl = "api.foursquare.com"
        }
    }
}


internal fun provideBaseHttpClient(
    engine: HttpClientEngine,
    options: HttpClientOptions,
    config: HttpClientConfig<*>.() -> Unit = {}
) = HttpClient(engine) {
    defaultRequest {

        with(options) {
            when (this) {
                is HttpClientOptions.Foursquare -> {
                    url {
                        host = baseUrl
                        protocol = URLProtocol.HTTPS
                    }
                    header(HttpHeaders.Authorization, apiKeyProvider.apiKey())
                }
            }
        }
    }

    // TODO: install logging only on debug build
    install(Logging) {
        logger = Logger.SIMPLE
        level = LogLevel.ALL
    }

    install(ContentNegotiation) {
        json(KtxSerializer.json)
    }
    config()
}


@OptIn(ExperimentalSerializationApi::class)
internal object KtxSerializer {
    val json = Json {
        isLenient = true
        ignoreUnknownKeys = true
        encodeDefaults = true
        explicitNulls = false
        coerceInputValues = true
    }
}