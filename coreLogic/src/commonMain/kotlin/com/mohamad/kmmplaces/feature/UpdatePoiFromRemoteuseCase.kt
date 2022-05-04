package com.mohamad.kmmplaces.feature

import com.mohamad.kmmplaces.CoreError
import com.mohamad.kmmplaces.data.PoiRepository
import com.mohamad.kmmplaces.functional.flatMap
import com.mohamad.kmmplaces.util.Either

class UpdatePoiFromRemoteUseCase(
    private val poiRepository: PoiRepository
) {
    suspend operator fun invoke(): Either<CoreError, Unit> = poiRepository.fetchFromRemote().flatMap {
        poiRepository.storeResult(it)
    }
}