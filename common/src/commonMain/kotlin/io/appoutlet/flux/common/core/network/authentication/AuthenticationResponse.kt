package io.appoutlet.flux.common.core.network.authentication

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class AuthenticationResponse(
    val kind: String,
    val localId: String,
    val email: String,
    val displayName: String,
    val idToken: String,
    val registered: Boolean,
    @Transient val refreshToken: String? = null,
    @Transient val expiresIn: String? = null,
)
