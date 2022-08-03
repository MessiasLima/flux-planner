package io.appoutlet.flux.common.feature.signin

import io.appoutlet.flux.common.domain.authentication.AuthenticationInteractor
import io.appoutlet.flux.common.domain.user.UserInteractor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SignInOrchestrator(
    private val authenticationInteractor: AuthenticationInteractor,
    private val userInteractor: UserInteractor,
) {
    fun signIn(email: String, password: String): Flow<Unit> {
        return authenticationInteractor.signIn(email, password)
            .map { userInteractor.save(it) }
    }
}
