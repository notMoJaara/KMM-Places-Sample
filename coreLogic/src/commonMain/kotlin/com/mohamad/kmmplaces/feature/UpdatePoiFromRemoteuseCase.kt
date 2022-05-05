package com.mohamad.kmmplaces.feature

import com.mohamad.kmmplaces.CoreError
import com.mohamad.kmmplaces.data.Location
import com.mohamad.kmmplaces.data.PoiRepository
import com.mohamad.kmmplaces.functional.flatMap
import com.mohamad.kmmplaces.util.Either

class UpdatePoiFromRemoteUseCase(
    private val poiRepository: PoiRepository
) {
    suspend operator fun invoke(location: Location): Either<CoreError, Unit> = poiRepository.fetchFromRemote(location.lat.toString(), location.long.toString()).flatMap {
        poiRepository.storeResult(it)
    }
}