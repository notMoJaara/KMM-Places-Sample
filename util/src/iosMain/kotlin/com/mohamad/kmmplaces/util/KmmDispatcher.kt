package com.mohamad.kmmplaces.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainCoroutineDispatcher

actual object KmmDispatcherImpl : KmmDispatcher {
    override val default: CoroutineDispatcher
        get() = Dispatchers.Default
    override val main: MainCoroutineDispatcher
        get() = Dispatchers.Main
    override val unconfined: CoroutineDispatcher
        get() = Dispatchers.Unconfined
    override val io: CoroutineDispatcher
        get() = Dispatchers.Default
}
