package com.mohamad.kmmplaces.network.http_client

import io.ktor.client.engine.*
import io.ktor.client.engine.okhttp.*

actual fun httpEngineProvider(): HttpClientEngine = OkHttp.create()