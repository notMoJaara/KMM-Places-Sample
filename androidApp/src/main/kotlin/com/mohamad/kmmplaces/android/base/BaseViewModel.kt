package com.mohamad.kmmplaces.android.base

import androidx.lifecycle.ViewModel
import com.mohamad.kmmplaces.android.util.SingleLiveEvent

abstract class BaseViewModel: ViewModel() {
    var isLoading = SingleLiveEvent<Boolean>()
    var errorMessage = SingleLiveEvent<String>()
}