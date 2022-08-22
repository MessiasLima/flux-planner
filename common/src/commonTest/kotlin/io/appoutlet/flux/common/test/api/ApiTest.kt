package io.appoutlet.flux.common.test.api

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine

interface ApiTest {
    val mockEngine: MockEngine
    val mockHttpClient: HttpClient

    fun addResponseResolver(url: String, response: ResponseResolver)
}
