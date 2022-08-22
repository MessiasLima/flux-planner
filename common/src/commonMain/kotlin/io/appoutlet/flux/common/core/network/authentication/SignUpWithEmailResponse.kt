package io.appoutlet.flux.common.core.network.authentication

import kotlinx.serialization.Serializable

@Serializable
data class SignUpWithEmailResponse(
    val kind: String,
    val idToken: String,
    val email: String,
    val refreshToken: String? = null,
    val expiresIn: String? = null,
    val localId: String,
)
