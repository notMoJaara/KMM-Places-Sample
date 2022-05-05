package com.mohamad.kmmplaces.android.ui.google_maps

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.mohamad.kmmplaces.android.base.BaseFragment
import com.mohamad.kmmplaces.android.ui.poi_list.PoiListViewModel
import com.mohamad.kmmplaces.data.Location
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GoogleMapsFragment : BaseFragment<PoiListViewModel>(
    PoiListViewModel::class.java,
    PoiListViewModel.isShared
) {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ComposeView(requireContext()).apply {
            setContent { GoogleMapsScreen(poiListViewModel = viewModel) }
        }

    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GoogleMapsScreen(poiListViewModel: PoiListViewModel) {

    val cameraPositionState = CameraPositionState(
        position = CameraPosition.fromLatLngZoom(poiListViewModel.centerSearchLocation.toLatLng(), 15f)
    )
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        // TODO: group up markers that are near to each other inorder to avoid screen clutter
        poiListViewModel.poiState.forEach {
            Marker(
                state = MarkerState(position = it.location.toLatLng()),
                title = it.name,
                snippet = it.address.formattedAddress
            )
        }
    }

}

private fun Location.toLatLng() = LatLng(lat, long)