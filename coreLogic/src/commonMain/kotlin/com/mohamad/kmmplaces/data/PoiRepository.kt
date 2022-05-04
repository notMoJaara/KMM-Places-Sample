package com.mohamad.kmmplaces.data

import com.mohamad.kmmplaces.CoreError
import com.mohamad.kmmplaces.NetworkError
import com.mohamad.kmmplaces.functional.map
import com.mohamad.kmmplaces.localStorage.dao.PoiDAO
import com.mohamad.kmmplaces.network.foursquare.nearby.Location
import com.mohamad.kmmplaces.network.foursquare.nearby.NearbyApi
import com.mohamad.kmmplaces.util.Either
import com.mohamad.kmmplaces.util.SecureProperties
import com.mohamad.kmmplaces.wrapRemoteRequest
import com.mohamad.kmmplaces.wrapStorageRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface PoiRepository {
    suspend fun observablePoiList(): Either<CoreError, Flow<List<Poi>>>
    suspend fun storeResult(result: List<Poi>): Either<CoreError, Unit>
    suspend fun fetchFromRemote(
        lat: String = SecureProperties.defLat,
        long: String = SecureProperties.defLong
    ): Either<NetworkError, List<Poi>>
}

class PoiRepositoryImpl(
    private val nearbyApi: NearbyApi,
    private val poiDAO: PoiDAO,
    private val poiMapper: PoiMapper
) : PoiRepository {
    override suspend fun observablePoiList(): Either<CoreError, Flow<List<Poi>>> =
        wrapStorageRequest { poiDAO.selectAllFlow().map { it.map((poiMapper::fromEntity)) } }

    override suspend fun storeResult(result: List<Poi>): Either<CoreError, Unit> =
        wrapStorageRequest { poiDAO.removeAndInsertNewData(result.map(poiMapper::toEntity)) }

    override suspend fun fetchFromRemote(lat: String, long: String): Either<NetworkError, List<Poi>> = wrapRemoteRequest {
        NearbyApi.NearbyParam(location = Location(lat, long)).let {
            nearbyApi.findNearbyPlaces(it)
        }
    }.map { it.results.map(poiMapper::fromDTO) }
}