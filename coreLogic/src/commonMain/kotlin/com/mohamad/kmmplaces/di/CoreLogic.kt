package com.mohamad.kmmplaces.di

import com.mohamad.kmmplaces.data.PoiRepository
import com.mohamad.kmmplaces.data.PoiRepositoryImpl
import com.mohamad.kmmplaces.feature.ObservePoiListUseCase
import com.mohamad.kmmplaces.feature.UpdatePoiFromRemoteUseCase
import com.mohamad.kmmplaces.localStorage.PlacesDBProvider
import com.mohamad.kmmplaces.network.foursquare.FoursquareService

expect class CoreLogic : CoreLogicCommon

abstract class CoreLogicCommon {
    abstract fun createDataBase(): PlacesDBProvider

    private  val database: PlacesDBProvider by lazy { createDataBase() }
    private val foursquareService: FoursquareService by lazy { FoursquareService() }

    private val poiRepository: PoiRepository get() = PoiRepositoryImpl(foursquareService.nearbyApi, database.poiDAO)

    val observePoiListUseCase: ObservePoiListUseCase get() = ObservePoiListUseCase(poiRepository)
    val updatePoiFromRemoteUseCase: UpdatePoiFromRemoteUseCase get() = UpdatePoiFromRemoteUseCase(poiRepository)

}