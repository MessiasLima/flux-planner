package io.appoutlet.flux.common.domain.user

import io.appoutlet.flux.common.core.database.generated.UserEntity
import io.appoutlet.flux.common.core.network.authentication.AuthenticationResponse
import io.appoutlet.flux.common.core.network.authentication.LookUpResponse
import io.appoutlet.flux.common.core.network.authentication.SignUpWithEmailResponse
import io.appoutlet.flux.common.feature.splash.exception.UserNotLoggedException
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

    fun map(createAccountResponse: SignUpWithEmailResponse) = UserDomain(
        idToken = createAccountResponse.idToken,
        email = createAccountResponse.email,
        refreshToken = createAccountResponse.refreshToken,
        id = createAccountResponse.localId,
        displayName = "",
        registered = false
    )

    fun map(userDomain: UserDomain, lookUpResponse: LookUpResponse): UserDomain {
        val userResponse = lookUpResponse.users.firstOrNull() ?: throw UserNotLoggedException()

        return UserDomain(
            id = userResponse.localId,
            email = userResponse.email,
            displayName = userResponse.displayName,
            idToken = userDomain.idToken,
            refreshToken = userDomain.refreshToken,
            registered = userResponse.emailVerified
        )
    }
}
