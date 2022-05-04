package com.mohamad.kmmplaces.android.ui.poi_list

import com.mohamad.kmmplaces.android.base.BaseViewModel
import com.mohamad.kmmplaces.data.Poi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PoiListViewModel @Inject constructor(): BaseViewModel() {
    private val _poiList: MutableStateFlow<List<Poi>> = MutableStateFlow(emptyList())
    private val poiListFlow: StateFlow<List<Poi>> = _poiList

}