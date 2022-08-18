package io.appoutlet.flux.common.domain.authentication

import io.appoutlet.flux.common.core.network.authentication.UpdateProfileRequest
import io.appoutlet.flux.common.domain.user.UserDomain

class UpdateProfileRequestMapper {
    fun map(user: UserDomain, displayName: String) = UpdateProfileRequest(
        idToken = user.idToken,
        displayName = displayName,
    )
}
