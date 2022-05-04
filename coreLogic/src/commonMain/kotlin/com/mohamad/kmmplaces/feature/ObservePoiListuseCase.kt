package com.mohamad.kmmplaces.feature

import com.mohamad.kmmplaces.data.Poi
import com.mohamad.kmmplaces.data.PoiRepository
import com.mohamad.kmmplaces.functional.fold
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

class ObservePoiListUseCase(
    private val poiRepository: PoiRepository
) {
    suspend operator fun invoke(): Flow<List<Poi>> = poiRepository.observablePoiList().fold({ emptyFlow() }, { it })
}