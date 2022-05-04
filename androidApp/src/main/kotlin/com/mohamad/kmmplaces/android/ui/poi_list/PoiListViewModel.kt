package com.mohamad.kmmplaces.android.ui.poi_list

import com.mohamad.kmmplaces.android.base.BaseViewModel
import com.mohamad.kmmplaces.android.util.launchOnDefault
import com.mohamad.kmmplaces.android.util.launchOnIO
import com.mohamad.kmmplaces.android.util.launchOnMain
import com.mohamad.kmmplaces.android.util.onFailure
import com.mohamad.kmmplaces.data.Poi
import com.mohamad.kmmplaces.feature.ObservePoiListUseCase
import com.mohamad.kmmplaces.feature.UpdatePoiFromRemoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PoiListViewModel @Inject constructor(
    private val observePoiListUseCase: ObservePoiListUseCase,
    private val updatePoiFromRemoteUseCase: UpdatePoiFromRemoteUseCase
) : BaseViewModel() {
    private val _poiList: MutableStateFlow<List<Poi>> = MutableStateFlow(emptyList())
    private val poiListFlow: StateFlow<List<Poi>> = _poiList

    init {
        launchOnMain {
            updatePoiFromRemoteUseCase().onFailure {
                // TODO: better error handling (E.g. based on error type)
                updateErrorMessage(it.toString())
            }
        }
    }
}