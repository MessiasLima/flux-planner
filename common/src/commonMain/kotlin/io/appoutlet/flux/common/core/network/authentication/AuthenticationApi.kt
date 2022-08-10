package io.appoutlet.flux.common.core.network.authentication

import io.appoutlet.flux.common.core.network.getResult
import io.ktor.client.HttpClient
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AuthenticationApi(
    private val httpClient: HttpClient
) {
    suspend fun authenticate(authenticationRequest: AuthenticationRequest): AuthenticationResponse {
        val httpResponse = httpClient.post(urlString = SIGN_IN_END_POINT) {
            parameter("key", SIGN_IN_API_KEY)
            contentType(ContentType.Application.Json)
            setBody(authenticationRequest)
        }

        return httpResponse.getResult()
    }

    companion object {
        internal const val SIGN_IN_END_POINT =
            "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword"
        internal const val SIGN_IN_API_KEY = "AIzaSyDnD1dMVG9WmGC8ZcHKbkW3u6pn9utqMgs"
    }
}
