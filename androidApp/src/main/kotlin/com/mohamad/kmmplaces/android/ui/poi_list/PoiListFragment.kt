package com.mohamad.kmmplaces.android.ui.poi_list

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mohamad.kmmplaces.android.R
import com.mohamad.kmmplaces.android.base.BaseFragment
import com.mohamad.kmmplaces.data.Address
import com.mohamad.kmmplaces.data.Location
import com.mohamad.kmmplaces.data.Poi
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PoiListFragment : BaseFragment<PoiListViewModel>(
    PoiListViewModel::class.java,
    false
) {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ComposeView(requireContext()).apply {
            setContent { PoiListScreen(poiListViewModel = viewModel) }
        }
    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PoiListScreen(poiListViewModel: PoiListViewModel) {
    val state = poiListViewModel.poiListFlow.collectAsState(initial = emptyList())
    LazyColumn {
        items(
            state.value
        ) { poi ->
            PoiListItem(poi) {}
        }
    }

}


@Preview
@Composable
fun PoiListItemPreview() {
    PoiListItem(
        poi = Poi(
            "id",
            "poi name",
            Location(12.3, 12.3),
            6,
            Address("Pariser Platzn", "Germany", "Pariser Platz, 10117 Berlin", "DE", "10117", "DE")
        )
    ) {}
}

@Composable
fun PoiListItem(poi: Poi, onShareClick: (poi: Poi) -> Unit) {
    Row(
        // TODO: handle padding in more scalable way and not magic numbers
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = poi.name,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = poi.address.formattedAddress,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }
        Button(onClick = { onShareClick(poi) }) {
            Icon(
                imageVector = Icons.Filled.Share,
                tint = Color.White,
                contentDescription = stringResource(R.string.share_location_btn_description)
            )
        }
    }

}


