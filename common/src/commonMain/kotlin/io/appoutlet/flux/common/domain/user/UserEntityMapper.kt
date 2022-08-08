package io.appoutlet.flux.common.domain.user

import io.appoutlet.flux.common.core.database.UserEntity
import io.appoutlet.flux.common.util.toLong

class UserEntityMapper {
    fun map(userDomain: UserDomain) = UserEntity(
        id = userDomain.id,
        email = userDomain.email,
        displayName = userDomain.displayName,
        idToken = userDomain.idToken,
        refreshToken = userDomain.refreshToken,
        registered = userDomain.registered.toLong()
    )
}
