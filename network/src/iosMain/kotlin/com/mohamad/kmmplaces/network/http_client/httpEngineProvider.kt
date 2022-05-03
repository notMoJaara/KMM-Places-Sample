package com.mohamad.kmmplaces.network.http_client

import io.ktor.client.engine.*
import io.ktor.client.engine.darwin.*

actual fun httpEngineProvider(): HttpClientEngine = Darwin.create {
    pipelining = true
}