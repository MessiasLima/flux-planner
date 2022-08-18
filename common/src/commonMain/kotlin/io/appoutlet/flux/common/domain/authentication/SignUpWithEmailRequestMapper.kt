package io.appoutlet.flux.common.domain.authentication

import io.appoutlet.flux.common.core.network.authentication.SignUpWithEmailRequest

class SignUpWithEmailRequestMapper {
    fun map(email: String, password: String) = SignUpWithEmailRequest(
        email = email,
        password = password,
    )
}
