package io.appoutlet.flux.common.core.network.authentication

import kotlinx.serialization.Serializable

@Serializable
data class AuthenticationRequest(
    val email: String,
    val password: String,
    val returnSecureToken: Boolean = true
)
