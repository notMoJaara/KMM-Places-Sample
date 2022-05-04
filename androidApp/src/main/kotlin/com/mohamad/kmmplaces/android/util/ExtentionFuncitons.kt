package com.mohamad.kmmplaces.android.util

import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope

fun Fragment.launchOnLifecycleScope(execute: suspend () -> Unit) {
    this.lifecycleScope.launchWhenCreated {
        execute()
    }
}