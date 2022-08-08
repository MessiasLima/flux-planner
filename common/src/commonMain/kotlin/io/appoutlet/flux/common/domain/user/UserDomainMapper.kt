package io.appoutlet.flux.common.domain.user

import io.appoutlet.flux.common.core.database.UserEntity
import io.appoutlet.flux.common.core.network.authentication.AuthenticationResponse
import io.appoutlet.flux.common.util.toBoolean

class UserDomainMapper {
    fun map(authenticationResponse: AuthenticationResponse) = UserDomain(
        id = authenticationResponse.localId,
        email = authenticationResponse.email,
        displayName = authenticationResponse.displayName,
        idToken = authenticationResponse.idToken,
        refreshToken = authenticationResponse.refreshToken,
        registered = authenticationResponse.registered,
    )

    fun map(userEntity: UserEntity) = UserDomain(
        id = userEntity.id,
        email = userEntity.email,
        displayName = userEntity.displayName,
        idToken = userEntity.idToken,
        refreshToken = userEntity.refreshToken,
        registered = userEntity.registered.toBoolean(),
    )
}
