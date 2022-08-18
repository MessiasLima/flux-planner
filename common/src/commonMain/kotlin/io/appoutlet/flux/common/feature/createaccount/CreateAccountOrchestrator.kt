package io.appoutlet.flux.common.feature.createaccount

import io.appoutlet.flux.common.domain.authentication.AuthenticationInteractor
import io.appoutlet.flux.common.domain.user.UserInteractor
import kotlinx.coroutines.flow.Flow

class CreateAccountOrchestrator(
    private val userInteractor: UserInteractor,
    private val authenticationInteractor: AuthenticationInteractor
) {
    fun createAccount(name: String, email: String, password: String): Flow<Unit> {
        authenticationInteractor.createUser(email, password)
    }
}
