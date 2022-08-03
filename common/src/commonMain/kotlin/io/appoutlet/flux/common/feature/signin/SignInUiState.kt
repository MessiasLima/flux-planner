package io.appoutlet.flux.common.feature.signin

sealed class SignInUiState {
    object Idle : SignInUiState()
    object Loading : SignInUiState()
    class Error(val throwable: Throwable) : SignInUiState()
    object Success : SignInUiState()
}
