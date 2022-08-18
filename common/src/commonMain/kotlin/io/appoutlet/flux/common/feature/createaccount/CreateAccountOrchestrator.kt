package io.appoutlet.flux.common.feature.createaccount

import io.appoutlet.flux.common.domain.authentication.AuthenticationInteractor
import io.appoutlet.flux.common.domain.user.UserDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CreateAccountOrchestrator(
    private val authenticationInteractor: AuthenticationInteractor
) {
    fun createAccount(name: String, email: String, password: String): Flow<UserDomain> {
        return authenticationInteractor.createUser(email, password)
            .map { user ->
                authenticationInteractor.updateDisplayName(user, name)
                authenticationInteractor.sendEmailConfirmation(user)
                user.copy(displayName = name)
            }
    }
}
