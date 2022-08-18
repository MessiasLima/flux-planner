package io.appoutlet.flux.common.domain.authentication

import io.appoutlet.flux.common.core.network.authentication.AuthenticationApi
import io.appoutlet.flux.common.domain.user.UserDomainMapper
import kotlinx.coroutines.flow.flow

class AuthenticationInteractor(
    private val authenticationApi: AuthenticationApi,
    private val authenticationRequestMapper: AuthenticationRequestMapper,
    private val userDomainMapper: UserDomainMapper,
) {
    fun signIn(email: String, password: String) = flow {
        val authenticationRequest = authenticationRequestMapper.map(email, password)
        val authenticationResponse = authenticationApi.authenticate(authenticationRequest)
        val userDomain = userDomainMapper.map(authenticationResponse)
        emit(userDomain)
    }

    fun createUser(email: String, password: String) = flow {
        val authenticationRequest = authenticationRequestMapper.map(email, password)
        val createAccountResponse = authenticationApi.createAccount(authenticationRequest)
    }
}
