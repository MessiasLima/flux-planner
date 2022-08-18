package io.appoutlet.flux.common.core.network.authentication

import kotlinx.serialization.Serializable

@Serializable
data class SendEmailConfirmationRequest(
    val requestType: String = "VERIFY_EMAIL",
    val idToken: String,
)
