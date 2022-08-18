package io.appoutlet.flux.common.feature.createaccount

import io.appoutlet.flux.common.domain.authentication.AuthenticationInteractor
import io.appoutlet.flux.common.domain.user.UserDomain
import io.appoutlet.flux.common.domain.user.UserInteractor
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.map

@FlowPreview
class CreateAccountOrchestrator(
    private val authenticationInteractor: AuthenticationInteractor,
    private val userInteractor: UserInteractor,
) {
    fun createAccount(name: String, email: String, password: String): Flow<UserDomain> {
        return authenticationInteractor.createUser(email, password)
            .flatMapMerge { user ->
                authenticationInteractor.updateDisplayName(user, name)
                authenticationInteractor.sendEmailConfirmation(user)
                authenticationInteractor.signIn(email, password)
            }.map { userInteractor.save(it) }
    }
}
