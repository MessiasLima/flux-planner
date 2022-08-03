package io.appoutlet.flux.common.domain.authentication

import io.appoutlet.flux.common.core.network.authentication.AuthenticationApi
import io.appoutlet.flux.common.core.network.authentication.AuthenticationResponse
import io.appoutlet.flux.common.domain.user.UserDomain
import io.appoutlet.flux.common.domain.user.UserDomainMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class AuthenticationInteractor(
    private val authenticationApi: AuthenticationApi,
    private val authenticationRequestMapper: AuthenticationRequestMapper,
    private val userDomainMapper: UserDomainMapper,
) {
    fun signIn(email: String, password: String): Flow<UserDomain> {
        val authenticationRequest = authenticationRequestMapper.map(email, password)
        return flow<AuthenticationResponse> { authenticationApi.authenticate(authenticationRequest) }
            .map { userDomainMapper.map(it) }
    }
}
