package io.appoutlet.flux.common.domain.authentication

import io.appoutlet.flux.common.core.network.authentication.AuthenticationRequest

class AuthenticationRequestMapper {
    fun map(email: String, password: String) = AuthenticationRequest(
        email = email,
        password = password,
    )
}
