package com.mohamad.kmmplaces.android.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mohamad.kmmplaces.android.util.launchOnLifecycleScope


abstract class BaseFragment<ViewModel : BaseViewModel>(
    private val viewModelClass: Class<ViewModel>,
    private val isSharedViewModel: Boolean
) : Fragment() {

    protected lateinit var viewModel: ViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = if (isSharedViewModel) {
            ViewModelProvider(requireActivity())[viewModelClass]
        } else {
            ViewModelProvider(this)[viewModelClass]
        }
        launchOnLifecycleScope {
            viewModel.errorMessage.observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }
    }
}