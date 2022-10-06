package io.appoutlet.flux.common.feature.emailverification

import io.appoutlet.flux.common.domain.authentication.AuthenticationInteractor
import io.appoutlet.flux.common.domain.user.UserInteractor

class EmailVerificationOrchestrator(
    private val userInteractor: UserInteractor,
    private val authenticationInteractor: AuthenticationInteractor,
) {
    suspend fun resendVerificationEmail() {
        val user = userInteractor.getLoggedUser()
        authenticationInteractor.sendEmailConfirmation(user)
    }

    fun logout() {
        userInteractor.deleteAll()
    }
}
