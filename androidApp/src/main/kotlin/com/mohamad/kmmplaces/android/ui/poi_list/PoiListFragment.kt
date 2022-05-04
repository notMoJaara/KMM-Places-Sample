package com.mohamad.kmmplaces.android.ui.poi_list

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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PoiListFragment: BaseFragment<PoiListViewModel>(
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
    Scaffold(
        content = {
            Text(text = "list fragment")
        }
    )
}

