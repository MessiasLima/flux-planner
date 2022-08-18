package io.appoutlet.flux.common.domain.authentication

import io.appoutlet.flux.common.core.network.authentication.SendEmailConfirmationRequest
import io.appoutlet.flux.common.domain.user.UserDomain

class SendEmailConfirmationRequestMapper {
    fun map(user: UserDomain) = SendEmailConfirmationRequest(
        idToken = user.idToken
    )
}
