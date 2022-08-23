package io.appoutlet.flux.common.core.network.authentication

import kotlinx.serialization.Serializable

@Serializable
data class LookUpResponse(
    val kind: String,
    val users: List<LookUpUserResponse>
)

@Serializable
data class LookUpUserResponse(
    val localId: String,
    val email: String,
    val displayName: String,
    val emailVerified: Boolean,
)
