package com.mohamad.kmmplaces.android.ui.google_maps

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import com.mohamad.kmmplaces.android.base.BaseFragment

class GoogleMapsFragment : BaseFragment<GoogleMapsViewModel>(
    GoogleMapsViewModel::class.java,
    false
) {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ComposeView(requireContext()).apply {
            setContent { GoogleMapsScreen(poiListViewModel = viewModel) }
        }

    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GoogleMapsScreen(poiListViewModel: GoogleMapsViewModel) {
    Scaffold(
        content = {
            Text(text = "google maps fragment")
        }
    )
}