package io.appoutlet.flux.common.core.network.authentication

import io.appoutlet.flux.common.core.network.common.Accounts
import io.appoutlet.flux.common.core.network.common.Route
import io.appoutlet.flux.common.core.network.getResult
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AuthenticationApi(private val httpClient: HttpClient) {
    suspend fun signInWithPassword(authenticationRequest: AuthenticationRequest): AuthenticationResponse {
        val httpResponse = httpClient.post(urlString = Route.Accounts.signInWithPassword()) {
            setupRequest()
            setBody(authenticationRequest)
        }

        return httpResponse.getResult()
    }

    suspend fun signUpWithEmail(authenticationRequest: SignUpWithEmailRequest): SignUpWithEmailResponse {
        val httpResponse = httpClient.post(urlString = Route.Accounts.signUp()) {
            setupRequest()
            setBody(authenticationRequest)
        }

        return httpResponse.getResult()
    }

    suspend fun updateProfile(updateProfileRequest: UpdateProfileRequest): UpdateProfileResponse {
        val httpResponse = httpClient.post(urlString = Route.Accounts.updateProfile()) {
            setupRequest()
            setBody(updateProfileRequest)
        }

        return httpResponse.getResult()
    }

    suspend fun sendEmailConfirmation(
        sendEmailConfirmationRequest: SendEmailConfirmationRequest,
    ): SendEmailConfirmationResponse {
        val httpResponse = httpClient.post(urlString = Route.Accounts.sendEmailConfirmation()) {
            setupRequest()
            setBody(sendEmailConfirmationRequest)
        }

        return httpResponse.getResult()
    }

    private fun HttpRequestBuilder.setupRequest() {
        parameter("key", SIGN_IN_API_KEY)
        contentType(ContentType.Application.Json)
    }

    companion object {
        internal const val SIGN_IN_API_KEY = "AIzaSyDnD1dMVG9WmGC8ZcHKbkW3u6pn9utqMgs"
    }
}
