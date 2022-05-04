package com.mohamad.kmmplaces.feature

import com.mohamad.kmmplaces.CoreError
import com.mohamad.kmmplaces.data.Poi
import com.mohamad.kmmplaces.data.PoiRepository
import com.mohamad.kmmplaces.util.Either
import kotlinx.coroutines.flow.Flow

class ObservePoiListUseCase(
    private val poiRepository: PoiRepository
) {
    suspend operator fun invoke(): Either<CoreError, Flow<List<Poi>>> = poiRepository.observablePoiList()
}