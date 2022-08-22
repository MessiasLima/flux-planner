package io.appoutlet.flux.common.core.network.authentication

import kotlinx.serialization.Serializable

@Serializable
data class UpdateProfileRequest(
    val idToken: String,
    val displayName: String? = null,
    val photoUrl: String? = null,
    val deleteAttribute: List<String>? = null,
    val returnSecureToken: Boolean = false,
)
