package io.appoutlet.flux.common.core.network.authentication

import kotlinx.serialization.Serializable

@Serializable
data class SendEmailConfirmationResponse(
    val kind: String,
    val email: String,
)
