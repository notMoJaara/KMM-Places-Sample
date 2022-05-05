package com.mohamad.kmmplaces.android.ui.poi_list

import androidx.compose.runtime.*
import com.mohamad.kmmplaces.android.base.BaseViewModel
import com.mohamad.kmmplaces.android.util.*
import com.mohamad.kmmplaces.android.util.onFailure
import com.mohamad.kmmplaces.data.Location
import com.mohamad.kmmplaces.data.Poi
import com.mohamad.kmmplaces.feature.ObservePoiListUseCase
import com.mohamad.kmmplaces.feature.UpdatePoiFromRemoteUseCase
import com.mohamad.kmmplaces.util.SecureProperties
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PoiListViewModel @Inject constructor(
    private val observePoiListUseCase: ObservePoiListUseCase,
    private val updatePoiFromRemoteUseCase: UpdatePoiFromRemoteUseCase
) : BaseViewModel() {
    var poiState by mutableStateOf<List<Poi>>(emptyList())
        private set

    // TODO: update the poi list every time centerSearchLocation changes
    var centerSearchLocation by mutableStateOf<Location>(getBaseLocation())
        private set

    init {
        launchOnMain {
            observePoiListUseCase().collect { poiList ->
                poiState = poiList
            }
        }
        launchOnMain {
            updatePoiFromRemoteUseCase(centerSearchLocation).onFailure {
                // TODO: better error handling (E.g. based on error type)
                updateErrorMessage("something went wrong")
            }
        }
    }

    private fun getBaseLocation() = Location(SecureProperties.defLat.toDouble(), SecureProperties.defLong.toDouble())

    companion object {
        const val isShared = true
    }
}