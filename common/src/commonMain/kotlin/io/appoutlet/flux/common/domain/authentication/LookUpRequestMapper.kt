package io.appoutlet.flux.common.domain.authentication

import io.appoutlet.flux.common.core.network.authentication.LookUpRequest
import io.appoutlet.flux.common.domain.user.UserDomain

class LookUpRequestMapper {
    fun map(user: UserDomain) = LookUpRequest(idToken = user.idToken)
}
