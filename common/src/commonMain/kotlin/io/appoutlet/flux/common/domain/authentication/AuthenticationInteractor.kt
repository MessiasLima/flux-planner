package io.appoutlet.flux.common.domain.authentication

import io.appoutlet.flux.common.core.network.authentication.AuthenticationApi
import io.appoutlet.flux.common.domain.user.UserDomain
import io.appoutlet.flux.common.domain.user.UserDomainMapper
import kotlinx.coroutines.flow.flow

class AuthenticationInteractor(
    private val authenticationApi: AuthenticationApi,
    private val authenticationRequestMapper: AuthenticationRequestMapper,
    private val userDomainMapper: UserDomainMapper,
    private val updateProfileRequestMapper: UpdateProfileRequestMapper,
    private val sendEmailConfirmationRequestMapper: SendEmailConfirmationRequestMapper,
    private val sighUpWithEmailRequestMapper: SignUpWithEmailRequestMapper,
    private val lookUpRequestMapper: LookUpRequestMapper,
) {
    fun signIn(email: String, password: String) = flow {
        val authenticationRequest = authenticationRequestMapper.map(email, password)
        val authenticationResponse = authenticationApi.signInWithPassword(authenticationRequest)
        val userDomain = userDomainMapper.map(authenticationResponse)
        emit(userDomain)
    }

    fun createUser(email: String, password: String) = flow {
        val authenticationRequest = sighUpWithEmailRequestMapper.map(email, password)
        val createAccountResponse = authenticationApi.signUpWithEmail(authenticationRequest)
        val userDomain = userDomainMapper.map(createAccountResponse)
        emit(userDomain)
    }

    suspend fun updateDisplayName(user: UserDomain, name: String) {
        val updateProfileRequest = updateProfileRequestMapper.map(user = user, displayName = name)
        authenticationApi.updateProfile(updateProfileRequest)
    }

    suspend fun sendEmailConfirmation(user: UserDomain) {
        val sendEmailConfirmationRequest = sendEmailConfirmationRequestMapper.map(user)
        authenticationApi.sendEmailConfirmation(sendEmailConfirmationRequest)
    }

    suspend fun lookUp(user: UserDomain): UserDomain {
        val lookUpRequest = lookUpRequestMapper.map(user)
        val lookUpResponse = authenticationApi.lookUp(lookUpRequest)
        return userDomainMapper.map(userDomain = user, lookUpResponse = lookUpResponse)
    }
}
