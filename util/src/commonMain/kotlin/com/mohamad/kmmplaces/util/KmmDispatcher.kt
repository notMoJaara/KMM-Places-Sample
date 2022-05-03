package com.mohamad.kmmplaces.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.MainCoroutineDispatcher


interface KmmDispatcher {
    val default: CoroutineDispatcher
    val main: MainCoroutineDispatcher
    val unconfined: CoroutineDispatcher
    val io: CoroutineDispatcher
}


expect object KmmDispatcherImpl : KmmDispatcher
