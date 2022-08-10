package io.appoutlet.flux.common.test.api

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine

interface ApiTest {
    val mockEngine: MockEngine
    val mockHttpClient: HttpClient

    fun setResponseResolver(url: String, response: ResponseResolver)
}
