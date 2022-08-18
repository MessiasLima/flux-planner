package io.appoutlet.flux.common.core.network.authentication

import kotlinx.serialization.Serializable

@Serializable
data class UpdateProfileResponse(
    val localId: String,
    val email: String,
    val displayName: String,
    val emailVerified: Boolean
)
