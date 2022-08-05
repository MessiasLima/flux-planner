package io.appoutlet.flux.common.feature.signin

import io.appoutlet.flux.common.domain.user.UserDomain

sealed class SignInUiState {
    object Idle : SignInUiState()
    object Loading : SignInUiState()
    class Error(val throwable: Throwable) : SignInUiState()
    class Success(user: UserDomain) : SignInUiState()
}
