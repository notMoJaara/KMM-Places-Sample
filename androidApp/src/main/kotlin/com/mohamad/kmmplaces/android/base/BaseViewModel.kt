package com.mohamad.kmmplaces.android.base

import androidx.lifecycle.ViewModel
import com.mohamad.kmmplaces.android.util.SingleLiveEvent

abstract class BaseViewModel : ViewModel() {
    var isLoading = SingleLiveEvent<Boolean>()
        private set
    var errorMessage = SingleLiveEvent<String>()
        private set

    fun updateErrorMessage(errorText: String) {
        errorMessage.value = errorText
    }

    fun startLoading() {
        isLoading.value = true
    }

    fun stopLoading() {
        isLoading.value = false
    }
}