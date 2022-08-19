package io.appoutlet.flux.common.test.api

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.MockRequestHandleScope
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.HttpRequestData
import io.ktor.client.request.HttpResponseData
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.security.InvalidParameterException

typealias ResponseResolver = (suspend MockRequestHandleScope.(HttpRequestData) -> HttpResponseData)

class ApiTestImpl : ApiTest {
    private val responseData = mutableMapOf<String, ResponseResolver>()

    override val mockEngine = MockEngine { request ->
        respond("")
        val responseResolver = responseData[request.url.toString()]

        if (responseResolver == null) {
            val message = "No response found for the given url: $request"
            println(message)
            throw InvalidParameterException(message)
        }

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

inline fun <reified T> MockRequestHandleScope.respond(
    content: T,
    status: HttpStatusCode = HttpStatusCode.OK,
    headers: Headers = headersOf(HttpHeaders.ContentType, "application/json")
): HttpResponseData = respond(Json.encodeToString(content), status, headers)
