package io.appoutlet.flux.common.core.network.authentication

import kotlinx.serialization.Serializable

@Serializable
data class SendEmailConfirmationRequest(
    val requestType: String,
    val idToken: String,
) {
    companion object {
        const val REQUEST_TYPE_VERIFY_EMAIL = "VERIFY_EMAIL"
    }
}
