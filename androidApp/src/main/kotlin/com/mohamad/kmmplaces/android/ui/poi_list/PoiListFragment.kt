package com.mohamad.kmmplaces.android.ui.poi_list

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mohamad.kmmplaces.android.R
import com.mohamad.kmmplaces.android.base.BaseFragment
import com.mohamad.kmmplaces.data.Address
import com.mohamad.kmmplaces.data.Location
import com.mohamad.kmmplaces.data.Poi
import com.mohamad.kmmplaces.data.getShareLink
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PoiListFragment : BaseFragment<PoiListViewModel>(
    PoiListViewModel::class.java,
    PoiListViewModel.isShared
) {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ComposeView(requireContext()).apply {
            setContent {
                PoiListScreen(poiListViewModel = viewModel) {
                    val shareIntent = Intent()
                    shareIntent.action = Intent.ACTION_SEND
                    shareIntent.type = "text/plain"
                    shareIntent.putExtra(Intent.EXTRA_TEXT, context.getString(R.string.share_poi, it.getShareLink()));
                    startActivity(Intent.createChooser(shareIntent, "sharing this cool place"))
                }
            }
        }
    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PoiListScreen(poiListViewModel: PoiListViewModel, onItemClick: (Poi) -> Unit) {
    val state = poiListViewModel.poiState
    LazyColumn {
        items(
            state
        ) { poi ->
            PoiListItem(poi) { onItemClick(poi) }
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
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        backgroundColor = Color.LightGray,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Row {
            Button(modifier = Modifier
                .padding(8.dp)
                .size(48.dp)
                .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
                .align(Alignment.CenterVertically),
                onClick = { onShareClick(poi) }) {
                Icon(
                    imageVector = Icons.Filled.Share,
                    tint = Color.White,
                    contentDescription = stringResource(R.string.share_location_btn_description)
                )
            }
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = poi.name,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    style = typography.h6
                )
                Text(
                    text = poi.address.formattedAddress,
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                    style = typography.caption
                )
            }
        }
    }

}


