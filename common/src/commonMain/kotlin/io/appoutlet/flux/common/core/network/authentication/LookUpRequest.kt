package io.appoutlet.flux.common.core.network.authentication

import kotlinx.serialization.Serializable

@Serializable
data class LookUpRequest(
    val idToken: String,
)
