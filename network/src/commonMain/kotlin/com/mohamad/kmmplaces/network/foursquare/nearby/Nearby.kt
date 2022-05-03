package com.mohamad.kmmplaces.network.foursquare.nearby

import com.mohamad.kmmplaces.network.foursquare.FoursquareResponse
import com.mohamad.kmmplaces.network.foursquare.model.PlacesListDTO
import com.mohamad.kmmplaces.network.foursquare.wrapFoursquareRequest
import com.mohamad.kmmplaces.util.KmmDispatcher
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.withContext

interface Nearby {
    suspend fun findNearbyPlaces(param: NearbyParam?): FoursquareResponse<PlacesListDTO>

    // TODO: investigate about possible param combinations (E.g. can altitude be provided without lat/long)
    data class NearbyParam(
        val location: Location? = null,
        val hacc: Double? = null,
        val altitude: Double? = null,
        val query: String? = null,
        val limit: Int? = null
    )
}

data class Location(
    val lat: String,
    val long: String
) {
    internal val toQueryParam get() = "$lat,$long"
}


class NearbyImpl(
    private val httpClient: HttpClient,
    private val dispatcher: KmmDispatcher
) : Nearby {
    override suspend fun findNearbyPlaces(param: Nearby.NearbyParam?): FoursquareResponse<PlacesListDTO> = wrapFoursquareRequest {
        withContext(dispatcher.io) {
            // TODO: add proper support for api version
            httpClient.get("v3/$PLACES_PATH/$NEARBY_PATH") {
                param?.apply {
                    location?.let { parameter(LOCATION_QUERY, it.toQueryParam) }
                    hacc?.let { parameter(HACC_QUERY, it) }
                    altitude?.let { parameter(ALTITUDE_QUERY, it) }
                    query?.let { parameter(QUERY_QUERY, it) }
                    limit?.let { parameter(LIMIT_QUERY, it) }
                }
            }
        }
    }

    private companion object {
        const val PLACES_PATH = "places"
        const val NEARBY_PATH = "nearby"
        const val LOCATION_QUERY = "ll"
        const val HACC_QUERY = "hacc"
        const val ALTITUDE_QUERY = "altitude"
        const val QUERY_QUERY = "query"
        const val LIMIT_QUERY = "limit"
    }

}