package io.appoutlet.flux.common.domain.user

import io.appoutlet.flux.common.core.network.authentication.AuthenticationResponse

class UserDomainMapper {
    fun map(authenticationResponse: AuthenticationResponse) = UserDomain(
        id = authenticationResponse.localId,
        email = authenticationResponse.email,
        displayName = authenticationResponse.displayName,
        idToken = authenticationResponse.idToken,
        refreshToken = authenticationResponse.refreshToken,
        registered = authenticationResponse.registered,
    )
}
