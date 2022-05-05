package com.mohamad.kmmplaces.network.foursquare

import com.mohamad.kmmplaces.network.foursquare.nearby.NearbyApi
import com.mohamad.kmmplaces.network.foursquare.nearby.NearbyApiImpl
import com.mohamad.kmmplaces.network.http_client.HttpClientOptions
import com.mohamad.kmmplaces.network.http_client.httpEngineProvider
import com.mohamad.kmmplaces.network.http_client.provideBaseHttpClient
import com.mohamad.kmmplaces.util.SecureProperties
import io.ktor.client.engine.*

interface FoursquareApiKeyProvider {
    fun apiKey(): String
}

internal object FoursquareApiKeyProviderImpl: FoursquareApiKeyProvider {
    override fun apiKey(): String = SecureProperties.fourSquareKey
}

class FoursquareService(
    private val engine: HttpClientEngine = httpEngineProvider(),
    private val foursquareApiKeyProvider: FoursquareApiKeyProvider = FoursquareApiKeyProviderImpl
    ) {

    private val authenticatedHttpClient by lazy {
        provideBaseHttpClient(engine, HttpClientOptions.Foursquare(foursquareApiKeyProvider))
    }

    val nearbyApi: NearbyApi get() = NearbyApiImpl(authenticatedHttpClient)
}
