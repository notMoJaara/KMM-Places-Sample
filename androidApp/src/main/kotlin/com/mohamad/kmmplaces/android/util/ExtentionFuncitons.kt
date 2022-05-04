@file:OptIn(ExperimentalContracts::class)

package com.mohamad.kmmplaces.android.util

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

inline fun Fragment.launchOnLifecycleScope(crossinline execute: suspend () -> Unit) {
    this.lifecycleScope.launchWhenCreated {
        execute()
    }
}


inline fun ViewModel.launchOnIO(crossinline execute: suspend () -> Unit) {
    contract {
        callsInPlace(execute, InvocationKind.EXACTLY_ONCE)
    }
    this.viewModelScope.launch(Dispatchers.IO) {
        execute()
    }
}

inline fun ViewModel.launchOnDefault(crossinline execute: suspend () -> Unit) {
    contract {
        callsInPlace(execute, InvocationKind.EXACTLY_ONCE)
    }
    this.viewModelScope.launch(Dispatchers.Default) {
        execute()
    }
}

inline fun ViewModel.launchOnMain(crossinline execute: suspend () -> Unit) {
    contract {
        callsInPlace(execute, InvocationKind.EXACTLY_ONCE)
    }
    this.viewModelScope.launch(Dispatchers.Main) {
        execute()
    }
}