package io.appoutlet.flux.common.test.api

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.MockRequestHandleScope
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.HttpRequestData
import io.ktor.client.request.HttpResponseData
import io.ktor.serialization.kotlinx.json.json
import java.security.InvalidParameterException

typealias ResponseResolver = (suspend MockRequestHandleScope.(HttpRequestData) -> HttpResponseData)

class ApiTestImpl : ApiTest {
    private val responseData = mutableMapOf<String, ResponseResolver>()

    override val mockEngine = MockEngine { request ->
        respond("")
        val responseResolver = responseData[request.url.toString()]
            ?: throw InvalidParameterException("No response found for the given url: $request")
        responseResolver(request)
    }

    override val mockHttpClient = HttpClient(mockEngine) {
        install(ContentNegotiation) {
            json()
        }
    }

    override fun setResponseResolver(url: String, response: ResponseResolver) {
        responseData[url] = response
    }
}
