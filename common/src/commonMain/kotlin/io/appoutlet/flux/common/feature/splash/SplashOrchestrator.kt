package io.appoutlet.flux.common.feature.splash

import io.appoutlet.flux.common.domain.authentication.AuthenticationInteractor
import io.appoutlet.flux.common.domain.user.UserDomain
import io.appoutlet.flux.common.domain.user.UserInteractor
import io.appoutlet.flux.common.feature.splash.exception.UserNotLoggedException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class SplashOrchestrator(
    private val userInteractor: UserInteractor,
    private val authenticationInteractor: AuthenticationInteractor,
) {
    fun verifyLoggedUser(): Flow<UserDomain> = flow {
        val user = getValidStoredUser()
        emit(user)
    }.map { user ->
        lookUp(user)
    }

    private fun getValidStoredUser(): UserDomain {
        val users = userInteractor.findAll()

        when (users.size) {
            1 -> return users.first()
            0 -> throw UserNotLoggedException("No user logged")
            else -> {
                userInteractor.deleteAll()
                throw UserNotLoggedException("Invalid state")
            }
        }
    }

    private suspend fun lookUp(user: UserDomain): UserDomain {
        try {
            return authenticationInteractor.lookUp(user)
        } catch (exception: UserNotLoggedException) {
            userInteractor.deleteAll()
            throw exception
        }
    }
}
