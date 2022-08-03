package io.appoutlet.flux.common.core.network.authentication

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

private const val SIGN_IN_END_POINT =
    "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword"
private const val SIGN_IN_API_KEY = "AIzaSyDnD1dMVG9WmGC8ZcHKbkW3u6pn9utqMgs"

class AuthenticationApi(
    private val httpClient: HttpClient
) {
    suspend fun authenticate(authenticationRequest: AuthenticationRequest): AuthenticationResponse {
        return httpClient.post(urlString = SIGN_IN_END_POINT) {
            parameter("key", SIGN_IN_API_KEY)
            contentType(ContentType.Application.Json)
            setBody(authenticationRequest)
        }.body()
    }
}
